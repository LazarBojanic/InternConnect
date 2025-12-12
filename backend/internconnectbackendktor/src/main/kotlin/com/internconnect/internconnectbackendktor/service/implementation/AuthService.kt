package com.internconnect.internconnectbackendktor.service.implementation

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.internconnectbackendktor.auth.JwtConfig
import com.internconnect.internconnectbackendktor.model.TokenClaims
import com.internconnect.internconnectbackendktor.model.TokenType
import com.internconnect.internconnectbackendktor.model.dto.response.TokenDto
import com.internconnect.internconnectbackendktor.model.dto.request.*
import com.internconnect.internconnectbackendktor.model.joined.CompanyJoined
import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import com.internconnect.internconnectbackendktor.model.raw.company.Company
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMember
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberRole
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberStatus
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.PasswordAuth
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshToken
import com.internconnect.internconnectbackendktor.model.raw.session.Session
import com.internconnect.internconnectbackendktor.model.raw.student.Student
import com.internconnect.internconnectbackendktor.model.raw.user.User
import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.service.specification.*
import io.ktor.server.auth.jwt.*
import org.mindrot.jbcrypt.BCrypt
import java.security.MessageDigest
import java.time.Instant
import java.util.*

class AuthService(
	private val userService: IUserService,
	private val sessionService: ISessionService,
	private val passwordAuthService: IPasswordAuthService,
	private val studentService: IStudentService,
	private val companyService: ICompanyService,
	private val companyMemberService: ICompanyMemberService,
	private val refreshTokenService: IRefreshTokenService,
	private val jwtConfig: JwtConfig
) : IAuthService {

	override suspend fun login(loginUserDto: LoginUserDto): TokenDto? {
		val now = Instant.now()
		require(loginUserDto.email.isNotBlank())
		require(loginUserDto.password.isNotBlank())

		val user = userService.getByEmail(loginUserDto.email) ?: throw Exception("invalid_credentials")
		val passwordAuth = passwordAuthService.getByUserId(user.id) ?: throw Exception("invalid_credentials")
		if (!BCrypt.checkpw(loginUserDto.password, passwordAuth.encryptedPassword)) throw Exception("invalid_credentials")

		val createdSession = sessionService.create(
			Session.createNew(
				userId = user.id,
				ip = loginUserDto.ip,
				userAgent = loginUserDto.userAgent,
				createdAt = now,
				updatedAt = now
			)
		) ?: throw Exception("failed_to_login")

		var companyId: UUID? = null
		if (user.role == UserRole.COMPANY_MEMBER) {
			companyId = companyMemberService.getById(user.id)?.company?.id
		}
		else {
			companyId = null
		}
		val nowPlusExpAccess = now.plus(jwtConfig.accessTtl)
		val access = issueJWT(
			TokenClaims(
				type = TokenType.ACCESS,
				userId = user.id,
				sessionId = createdSession.id,
				companyId = companyId,
				email = user.email,
				role = user.role,
				ip = loginUserDto.ip,
				userAgent = loginUserDto.userAgent,
				issuedAt = now,
				expiresAt = nowPlusExpAccess,
				revokedAt = null
			)
		) ?: throw Exception("failed_to_login")
		val nowPlusExpRefresh = now.plus(jwtConfig.refreshTtl)
		val refresh = issueJWT(
			TokenClaims(
				type = TokenType.REFRESH,
				userId = user.id,
				sessionId = createdSession.id,
				companyId = companyId,
				email = user.email,
				role = user.role,
				ip = loginUserDto.ip,
				userAgent = loginUserDto.userAgent,
				issuedAt = now,
				expiresAt = nowPlusExpRefresh,
				revokedAt = null
			)
		) ?: throw Exception("failed_to_login")

		val refreshHash = sha256(refresh) ?: throw Exception("failed_to_login")
		val parsedRefresh = JWT.decode(refresh)

		refreshTokenService.create(
			RefreshToken.createNew(
				sessionId = createdSession.id,
				hash = refreshHash,
				issuedAt = now,
				expiresAt = parsedRefresh.expiresAt.toInstant(),
				revokedAt = null,
				createdAt = now,
				updatedAt = now
			)
		) ?: throw Exception("failed_to_login")

		return TokenDto(access, refresh)
	}

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): UserJoined? {
		val now = Instant.now()
		require(registerStudentDto.email.isNotBlank())
		require(registerStudentDto.password.length >= 8)
		require(registerStudentDto.password == registerStudentDto.confirmPassword) { "password_mismatch" }

		val existing = userService.getByEmail(registerStudentDto.email)
		require(existing == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerStudentDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerStudentDto.email,
			firstName = registerStudentDto.firstName,
			lastName = registerStudentDto.lastName,
			role = UserRole.STUDENT,
			createdAt = now,
			updatedAt = now
		)
		val createdUser = userService.create(userToCreate) ?: return null

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUser.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt",
			createdAt = now,
			updatedAt = now
		)
		passwordAuthService.create(passwordAuthToCreate) ?: return null

		val studentToCreate = Student.createNew(
			userId = createdUser.id,
			grade = registerStudentDto.grade,
			schoolName = registerStudentDto.schoolName,
			bio = null,
			interests = null,
			avatarUrl = null,
			createdAt = now,
			updatedAt = now
		)
		studentService.create(studentToCreate) ?: return null

		return createdUser
	}

	override suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): UserJoined? {
		val now = Instant.now()
		require(registerCompanyMemberDto.userEmail.isNotBlank())
		require(registerCompanyMemberDto.password.length >= 8)
		require(registerCompanyMemberDto.password == registerCompanyMemberDto.confirmPassword) { "password_mismatch" }
		require(registerCompanyMemberDto.companyName.isNotBlank())
		require(registerCompanyMemberDto.companyIndustry.isNotBlank())

		val existingUser = userService.getByEmail(registerCompanyMemberDto.userEmail)
		require(existingUser == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerCompanyMemberDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerCompanyMemberDto.userEmail,
			firstName = registerCompanyMemberDto.userFirstName,
			lastName = registerCompanyMemberDto.userLastName,
			role = UserRole.COMPANY_MEMBER,
			createdAt = now,
			updatedAt = now
		)
		val createdUser = userService.create(userToCreate) ?: return null

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUser.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt",
			createdAt = now,
			updatedAt = now
		)
		passwordAuthService.create(passwordAuthToCreate) ?: return null

		val companyToCreate = Company(
			id = UUID.randomUUID(),
			name = registerCompanyMemberDto.companyName,
			industry = registerCompanyMemberDto.companyIndustry,
			website = null,
			logoUrl = null,
			country = null,
			city = null,
			about = null,
			createdAt = now,
			updatedAt = now
		)
		companyService.create(companyToCreate) ?: return null

		val companyMemberToCreate = CompanyMember.createNew(
			companyId = companyToCreate.id,
			userId = createdUser.id,
			role = CompanyMemberRole.OWNER,
			status = CompanyMemberStatus.ACTIVE
		)
		companyMemberService.create(companyMemberToCreate) ?: return null

		return createdUser
	}
	//TODO logout revoke session also maybe
	override suspend fun logoutCurrentSession(principal: JWTPrincipal): Boolean {
		val sid = principal.payload.getClaim("sid").asString()
		if (!sid.isNullOrBlank()) {
			refreshTokenService.revokeBySessionId(UUID.fromString(sid))
			return true
		}
		return false
	}

	override fun issueJWT(
		tokenClaims: TokenClaims,
	): String? {
		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withClaim("typ", tokenClaims.type.name)
			.withSubject(tokenClaims.userId.toString())
			.withClaim("sid", tokenClaims.sessionId.toString())
			.withClaim("companyId", tokenClaims.companyId?.toString())
			.withClaim("email", tokenClaims.email)
			.withClaim("role", tokenClaims.role.name)
			.withClaim("ip", tokenClaims.ip)
			.withClaim("userAgent", tokenClaims.userAgent)
			.withClaim("revokedAt", tokenClaims.revokedAt?.toString())
			.withIssuedAt(tokenClaims.issuedAt)
			.withExpiresAt(tokenClaims.expiresAt)
			.sign(jwtConfig.alg)
	}

	override fun verifier(): JWTVerifier {
		return JWT.require(jwtConfig.alg)
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.acceptLeeway(2)
			.build()
	}

	override fun sha256(input: String): String? {
		return MessageDigest.getInstance("SHA-256")
			.digest(input.toByteArray())
			.fold("") { str, byte -> str + "%02x".format(byte) }
	}

	override suspend fun refresh(tokenDto: TokenDto): TokenDto? {
		val now = Instant.now()
		val refresh = tokenDto.refresh ?: return null

		val decoded = JWT.decode(refresh)
		require(decoded.getClaim("typ").asString() == TokenType.REFRESH.name) { "invalid_token_type" }

		val refreshHash = sha256(refresh) ?: return null
		val stored = refreshTokenService.findActiveByHash(refreshHash) ?: return null

		require(decoded.subject == stored.sessionJoined.user.id.toString()) { "invalid_refresh" }
		require(decoded.getClaim("sid").asString() == stored.sessionJoined.id.toString()) { "invalid_refresh" }

		refreshTokenService.revokeById(stored.id)

		val nowPlusExpAccess = now.plus(jwtConfig.accessTtl)
		val access = issueJWT(
			TokenClaims(
				type = TokenType.ACCESS,
				userId = UUID.fromString(decoded.subject),
				sessionId = stored.sessionJoined.id,
				companyId = UUID.fromString(decoded.getClaim("companyId").toString()),
				email = stored.sessionJoined.user.email,
				role = stored.sessionJoined.user.role,
				ip = decoded.getClaim("ip").asString(),
				userAgent = decoded.getClaim("userAgent").asString(),
				issuedAt = now,
				expiresAt = nowPlusExpAccess,
				revokedAt = null,
			)
		) ?: return null
		val nowPlusExpRefresh = now.plus(jwtConfig.refreshTtl)
		val newRefresh = issueJWT(
			TokenClaims(
				type = TokenType.REFRESH,
				userId = UUID.fromString(decoded.subject),
				sessionId = stored.sessionJoined.id,
				companyId = UUID.fromString(decoded.getClaim("companyId").toString()),
				email = stored.sessionJoined.user.email,
				role = stored.sessionJoined.user.role,
				ip = decoded.getClaim("ip").asString(),
				userAgent = decoded.getClaim("userAgent").asString(),
				issuedAt = now,
				expiresAt = nowPlusExpRefresh,
				revokedAt = null,
			)
		) ?: return null

		val newParsed = JWT.decode(newRefresh)
		val newHash = sha256(newRefresh) ?: return null

		refreshTokenService.create(
			RefreshToken.createNew(
				sessionId = stored.sessionJoined.id,
				hash = newHash,
				issuedAt = newParsed.issuedAt.toInstant(),
				expiresAt = newParsed.expiresAt.toInstant(),
				revokedAt = null,
			)
		) ?: return null

		return TokenDto(access, newRefresh)
	}
}
