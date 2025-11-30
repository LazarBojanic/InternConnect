package com.internconnect.internconnectbackendktor.service.implementation

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.internconnectbackendktor.auth.JwtConfig
import com.internconnect.internconnectbackendktor.model.dto.TokenDto
import com.internconnect.internconnectbackendktor.model.dto.request.*
import com.internconnect.internconnectbackendktor.model.dto.response.*
import com.internconnect.internconnectbackendktor.model.joined.CompanyJoined
import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import com.internconnect.internconnectbackendktor.model.raw.company.Company
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMember
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberRole
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberStatus
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.PasswordAuth
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshToken
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
		val userJoined = userService.getByEmail(loginUserDto.email) ?: throw Exception("invalid_credentials")
		val passwordAuth = passwordAuthService.getByUserId(userJoined.id) ?: throw Exception("invalid_credentials")
		require(BCrypt.checkpw(loginUserDto.password, passwordAuth.encryptedPassword)) { "invalid_credentials" }
		val sessionId = UUID.randomUUID()
		val companyMemberJoined = companyMemberService.getById(userJoined.id) ?: throw Exception("invalid_credentials")
		val access = issueAccess(
			userId = userJoined.id,
			email = userJoined.email,
			role = userJoined.role.name,
			companyId = companyMemberJoined.company.id,
			sessionId = sessionId,
			time = now
		) ?: throw Exception("failed_to_login")

		val refresh = issueRefresh(
			userId = userJoined.id,
			email = userJoined.email,
			role = userJoined.role.name,
			companyId = companyMemberJoined.company.id,
			sessionId = sessionId,
			time = now
		) ?: throw Exception("failed_to_login")

		val refreshHash = sha256(refresh) ?: throw Exception("failed_to_login")

		val parsedRefresh = JWT.decode(refresh)

		val refreshTokenToCreate = RefreshToken.createNew(
			userId = userJoined.id,
			sessionId = sessionId,
			hash = refreshHash,
			issuedAt = parsedRefresh.issuedAt.toInstant(),
			expiresAt = parsedRefresh.expiresAt.toInstant(),
			revokedAt = null,
			userAgent = loginUserDto.userAgent,
			ip = loginUserDto.ip,
			createdAt = now,
			updatedAt = now
		)

		refreshTokenService.create(refreshTokenToCreate)
			?: throw Exception("failed_to_login")

		return TokenDto(access, refresh)
	}

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): UserJoined {
		val now = Instant.now()
		require(registerStudentDto.email.isNotBlank())
		require(registerStudentDto.password.length >= 8)
		require(registerStudentDto.password == registerStudentDto.confirmPassword) { "password_mismatch" }

		val userJoined = userService.getByEmail(registerStudentDto.email)
		require(userJoined == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerStudentDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerStudentDto.email,
			firstName = registerStudentDto.firstName,
			lastName = registerStudentDto.lastName,
			role = UserRole.STUDENT,
			createdAt = now,
			updatedAt = now
		)

		val createdUserJoined = userService.create(userToCreate) ?: throw Exception("failed_to_register")

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUserJoined.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt",
			createdAt = now,
			updatedAt = now
		)

		passwordAuthService.create(passwordAuthToCreate) ?: throw Exception("failed_to_register")

		val studentToCreate = Student.createNew(
			userId = createdUserJoined.id,
			grade = registerStudentDto.grade,
			schoolName = registerStudentDto.schoolName,
			bio = null,
			interests = null,
			avatarUrl = null,
			createdAt = now,
			updatedAt = now
		)

		studentService.create(studentToCreate)
			?: throw Exception("failed_to_register")

		return createdUserJoined
	}

	override suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): UserJoined? {
		val now = Instant.now()
		require(registerCompanyMemberDto.userEmail.isNotBlank())
		require(registerCompanyMemberDto.password.length >= 8)
		require(registerCompanyMemberDto.password == registerCompanyMemberDto.confirmPassword) { "password_mismatch" }
		require(registerCompanyMemberDto.companyName.isNotBlank())
		require(registerCompanyMemberDto.companyIndustry.isNotBlank())

		val userJoined = userService.getByEmail(registerCompanyMemberDto.userEmail)
		require(userJoined == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerCompanyMemberDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerCompanyMemberDto.userEmail,
			firstName = registerCompanyMemberDto.userFirstName,
			lastName = registerCompanyMemberDto.userLastName,
			role = UserRole.COMPANY_MEMBER,
			createdAt = now,
			updatedAt = now
		)

		val createdUserJoined = userService.create(userToCreate)
			?: throw Exception("failed_to_register")

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUserJoined.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt",
			createdAt = now,
			updatedAt = now
		)

		passwordAuthService.create(passwordAuthToCreate)
			?: throw Exception("failed_to_register")

		val existingCompanyJoined = companyService.getByName(registerCompanyMemberDto.companyName)
		require(existingCompanyJoined == null) { "company_name_taken" }

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

		companyService.create(companyToCreate)
			?: throw Exception("failed_to_register")

		val companyMemberToCreate = CompanyMember.createNew(
			companyId = companyToCreate.id,
			userId = createdUserJoined.id,
			role = CompanyMemberRole.OWNER,
			status = CompanyMemberStatus.ACTIVE
		)

		companyMemberService.create(companyMemberToCreate) ?: throw Exception("failed_to_register")

		return createdUserJoined
	}

	override suspend fun logoutCurrentSession(principal: JWTPrincipal): Boolean {
		val sid = principal.payload.getClaim("sid").asString()
		if (!sid.isNullOrBlank()) {
			refreshTokenService.revokeBySessionId(UUID.fromString(sid))
			return true
		}
		return false
	}

	override suspend fun logoutAllSessions(userId: UUID): Boolean {
		return refreshTokenService.revokeAllForUser(userId) > 0
	}

	override fun issueAccess(
		userId: UUID,
		email: String,
		role: String,
		companyId: UUID?,
		sessionId: UUID?,
		time: Instant?
	): String? {
		val now = time ?: Instant.now()
		val nowPlusExp = now.plus(jwtConfig.accessTtl)
		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("role", role)
			.withClaim("companyId", companyId?.toString())
			.withClaim("sid", sessionId?.toString())
			.withClaim("typ", "access")
			.withIssuedAt(now)
			.withExpiresAt(nowPlusExp)
			.sign(jwtConfig.alg)
	}

	override fun issueRefresh(
		userId: UUID,
		email: String,
		role: String,
		companyId: UUID?,
		sessionId: UUID?,
		time: Instant?
	): String? {
		val now = time ?: Instant.now()
		val nowPlusExp = now.plus(jwtConfig.refreshTtl)

		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("role", role)
			.withClaim("companyId", companyId?.toString())
			.withClaim("sid", sessionId?.toString())
			.withClaim("typ", "access")
			.withIssuedAt(now)
			.withExpiresAt(nowPlusExp)
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
		var refresh: String? = tokenDto.refresh
		var access: String? = null
		var newRefresh: String? = null
		if (refresh != null) {
			val decoded = JWT.decode(refresh)
			require(decoded.getClaim("typ").asString() == "refresh") { "invalid_token_type" }

			val refreshHash = sha256(refresh) ?: throw Exception("invalid_refresh")
			val storedRefreshTokenJoined = refreshTokenService.findActiveByHash(refreshHash) ?: throw Exception("invalid_refresh")

			require(decoded.subject == storedRefreshTokenJoined.user.id.toString()) { "invalid_refresh" }
			require(decoded.getClaim("sid").asString() == storedRefreshTokenJoined.sessionId.toString()) { "invalid_refresh" }

			refreshTokenService.revokeById(storedRefreshTokenJoined.id)

			access = issueAccess(
				userId = UUID.fromString(decoded.subject),
				email = decoded.getClaim("email").asString() ?: "",
				role = decoded.getClaim("role").asString() ?: "student",
				companyId = decoded.getClaim("companyId").asString()?.let { UUID.fromString(it) } ,
				sessionId = UUID.fromString(decoded.getClaim("sid").asString()),
				time = decoded.issuedAt.toInstant()
			)
				?: throw Exception("failed_to_refresh")

			newRefresh =
				issueRefresh(
					userId = UUID.fromString(decoded.subject),
					email = decoded.getClaim("email").asString() ?: "",
					role = decoded.getClaim("role").asString() ?: "student",
					companyId = decoded.getClaim("companyId").asString()?.let { UUID.fromString(it) } ,
					sessionId = UUID.fromString(decoded.getClaim("sid").asString()),
					time = decoded.issuedAt.toInstant()
				)
					?: throw Exception("failed_to_refresh")

			val newParsed = JWT.decode(newRefresh)
			val newHash = sha256(newRefresh) ?: throw Exception("failed_to_refresh")

			//TODO fix userAgent and ip
			refreshTokenService.create(
				RefreshToken.createNew(
					userId = UUID.fromString(decoded.subject),
					sessionId = UUID.fromString(decoded.getClaim("sid").asString()),
					hash = newHash,
					issuedAt = newParsed.issuedAt.toInstant(),
					expiresAt = newParsed.expiresAt.toInstant(),
					revokedAt = null,
					userAgent = null,
					ip = null
				)
			) ?: throw Exception("failed_to_refresh")
		}

		return TokenDto(access, newRefresh)
	}
}