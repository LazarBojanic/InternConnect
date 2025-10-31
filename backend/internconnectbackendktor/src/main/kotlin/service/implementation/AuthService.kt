package com.internconnect.service.implementation

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.auth.JwtConfig
import com.internconnect.database.dbQuery
import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.RefreshDto
import com.internconnect.dto.Token
import com.internconnect.dto.RegisterCompanyMemberDto
import com.internconnect.dto.RegisterStudentDto
import com.internconnect.model.company.Company
import com.internconnect.model.companymember.CompanyMember
import com.internconnect.model.companymember.CompanyMemberRole
import com.internconnect.model.companymember.CompanyMemberStatus
import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.student.Student
import com.internconnect.model.user.User
import com.internconnect.model.user.UserRole
import com.internconnect.service.specification.*
import io.ktor.server.auth.jwt.JWTPrincipal
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


	override suspend fun login(loginUserDto: LoginUserDto): Token? = dbQuery {
		require(loginUserDto.email.isNotBlank())
		require(loginUserDto.password.isNotBlank())

		val user = userService.getByEmail(loginUserDto.email)
			?: throw Exception("invalid_credentials")

		val passwordAuth = passwordAuthService.getByUserId(user.id)
			?: throw Exception("invalid_credentials")

		require(BCrypt.checkpw(loginUserDto.password, passwordAuth.encryptedPassword)) { "invalid_credentials" }

		val sessionId = UUID.randomUUID()

		val companyMember = companyMemberService.getByUserId(user.id)
		var company: Company? = null
		var companyId: UUID? = null
		if (companyMember != null) {
			company = companyService.getById(companyMember.companyId)
			if(company != null){
				companyId = company.id
			}
			else{
				companyId = UUID.fromString("00000000-0000-0000-0000-000000000000")
			}
		}

		val access = issueAccess(
			userId = user.id,
			email = user.email,
			userRole = user.userRole.name,
			companyId = companyId,
			sessionId = sessionId
		) ?: throw Exception("failed_to_login")

		val refresh = issueRefresh(user.id, user.email, user.userRole.name,companyId, sessionId)
			?: throw Exception("failed_to_login")

		val refreshHash = sha256(refresh) ?: throw Exception("failed_to_login")

		val parsedRefresh = JWT.decode(refresh)

		val refreshTokenToCreate = RefreshToken.createNew(
			userId = user.id,
			sessionId = sessionId,
			hash = refreshHash,
			issuedAt = parsedRefresh.issuedAt.toInstant(),
			expiresAt = parsedRefresh.expiresAt.toInstant(),
			revokedAt = null,
			userAgent = loginUserDto.userAgent,
			ip = loginUserDto.ip
		)

		refreshTokenService.create(refreshTokenToCreate)
			?: throw Exception("failed_to_login")

		Token(access, refresh)
	}

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User = dbQuery {
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
			userRole = UserRole.STUDENT
		)

		val createdUser = userService.create(userToCreate) ?: throw Exception("failed_to_register")

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUser.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt"
		)

		passwordAuthService.create(passwordAuthToCreate) ?: throw Exception("failed_to_register")

		val studentToCreate = Student.createNew(
			userId = createdUser.id,
			firstName = registerStudentDto.firstName,
			lastName = registerStudentDto.lastName,
			grade = registerStudentDto.grade,
			schoolName = registerStudentDto.schoolName,
			bio = null,
			interests = null,
			avatarUrl = null
		)

		studentService.create(studentToCreate)
			?: throw Exception("failed_to_register")

		createdUser
	}

	override suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): User? = dbQuery {
		val now = Instant.now()
		require(registerCompanyMemberDto.userEmail.isNotBlank())
		require(registerCompanyMemberDto.password.length >= 8)
		require(registerCompanyMemberDto.password == registerCompanyMemberDto.confirmPassword) { "password_mismatch" }
		require(registerCompanyMemberDto.companyName.isNotBlank())
		require(registerCompanyMemberDto.companyIndustry.isNotBlank())

		val existing = userService.getByEmail(registerCompanyMemberDto.userEmail)
		require(existing == null) { "email_taken" }


		val encryptedPassword = BCrypt.hashpw(registerCompanyMemberDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerCompanyMemberDto.userEmail,
			firstName = registerCompanyMemberDto.userFirstName,
			lastName = registerCompanyMemberDto.userLastName,
			userRole = UserRole.COMPANY_MEMBER
		)

		val createdUser = userService.create(userToCreate)
			?: throw Exception("failed_to_register")

		val passwordAuthToCreate = PasswordAuth.createNew(
			userId = createdUser.id,
			encryptedPassword = encryptedPassword,
			encryptionAlgorithm = "bcrypt"
		)

		passwordAuthService.create(passwordAuthToCreate)
			?: throw Exception("failed_to_register")

		val existingCompany = companyService.getByName(registerCompanyMemberDto.companyName)
		require(existingCompany == null) { "company_name_taken" }

		val companyToCreate = Company(
			id = UUID.randomUUID(),
			name = registerCompanyMemberDto.companyName,
			industry = registerCompanyMemberDto.companyIndustry,
			website = null,
			logoUrl = null,
			hqCountry = null,
			city = null,
			about = null,
			createdAt = now,
			updatedAt = now
		)

		companyService.create(companyToCreate)
			?: throw Exception("failed_to_register")

		val companyMemberToCreate = CompanyMember.createNew(
			companyId = companyToCreate.id,
			userId = createdUser.id,
			companyMemberRole = CompanyMemberRole.owner,
			companyMemberStatus = CompanyMemberStatus.active
		)

		companyMemberService.create(companyMemberToCreate) ?: throw Exception("failed_to_register")

		createdUser
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
		userRole: String,
		companyId: UUID?,
		sessionId: UUID?
	): String? {
		val now = Instant.now()
		val nowPlusExp = now.plus(jwtConfig.accessTtl)
		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("userRole", userRole)
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
		userRole: String,
		companyId: UUID?,
		sessionId: UUID?
	): String? {
		val now = Instant.now()
		val nowPlusExp = now.plus(jwtConfig.refreshTtl)

		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("userRole", userRole)
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

	override suspend fun refresh(refreshDto: RefreshDto): Token? {
		return dbQuery {
			var refresh: String? = refreshDto.refresh
			var access: String? = null
			var newRefresh: String? = null
			if(refresh != null){
				val decoded = JWT.decode(refresh)
				require(decoded.getClaim("typ").asString() == "refresh") { "invalid_token_type" }

				val refreshHash = sha256(refresh) ?: throw Exception("invalid_refresh")
				val stored = refreshTokenService.findActiveByHash(refreshHash) ?: throw Exception("invalid_refresh")

				require(decoded.subject == stored.userId.toString()) { "invalid_refresh" }
				require(decoded.getClaim("sid").asString() == stored.sessionId.toString()) { "invalid_refresh" }

				refreshTokenService.revokeById(stored.id)

				access = issueAccess(
					UUID.fromString(decoded.subject),
					decoded.getClaim("email").asString() ?: "",
					decoded.getClaim("userRole").asString() ?: "student",
					null,
					UUID.fromString(decoded.getClaim("sid").asString())
				)
					?: throw Exception("failed_to_refresh")

				newRefresh =
					issueRefresh(UUID.fromString(decoded.subject),
						decoded.getClaim("email").asString() ?: "",
						decoded.getClaim("userRole").asString() ?: "student",
						null,
						UUID.fromString(decoded.getClaim("sid").asString()))
						?: throw Exception("failed_to_refresh")

				val newParsed = JWT.decode(newRefresh)
				val newHash = sha256(newRefresh) ?: throw Exception("failed_to_refresh")

				refreshTokenService.create(
					RefreshToken.createNew(
						userId = UUID.fromString(decoded.subject),
						sessionId = UUID.fromString(decoded.getClaim("sid").asString()),
						hash = newHash,
						issuedAt = newParsed.issuedAt.toInstant(),
						expiresAt = newParsed.expiresAt.toInstant(),
						revokedAt = null,
						userAgent = refreshDto.userAgent,
						ip = refreshDto.ip
					)
				) ?: throw Exception("failed_to_refresh")
			}

			Token(access, newRefresh)
		}
	}
}