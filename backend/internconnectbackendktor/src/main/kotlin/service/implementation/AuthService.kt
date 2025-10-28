package com.internconnect.service.implementation

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.auth.JwtConfig
import com.internconnect.database.dbQuery
import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.Token
import com.internconnect.dto.RegisterCompanyDto
import com.internconnect.dto.RegisterStudentDto
import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.student.Student
import com.internconnect.model.user.User
import com.internconnect.model.user.UserRole
import com.internconnect.service.specification.*
import org.mindrot.jbcrypt.BCrypt
import java.security.MessageDigest
import java.time.Instant
import java.util.*


class AuthService(
	private val userService: IUserService,
	private val passwordAuthService: IPasswordAuthService,
	private val studentService: IStudentService,
	private val companyService: ICompanyService,
	private val refreshTokenService: IRefreshTokenService,
	private val jwtConfig: JwtConfig
) : IAuthService {

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User = dbQuery {
		require(registerStudentDto.email.isNotBlank())
		require(registerStudentDto.password.length >= 8)

		val existing = userService.getByEmail(registerStudentDto.email)
		require(existing == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerStudentDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerStudentDto.email,
			firstName = registerStudentDto.firstName,
			lastName = registerStudentDto.lastName,
			userRole = UserRole.student
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
	override suspend fun login(loginUserDto: LoginUserDto): Token? = dbQuery {
		require(loginUserDto.email.isNotBlank())
		require(loginUserDto.password.isNotBlank())

		val user = userService.getByEmail(loginUserDto.email)
			?: throw Exception("invalid_credentials")

		val passwordAuth = passwordAuthService.getByUserId(user.id)
			?: throw Exception("invalid_credentials")

		require(BCrypt.checkpw(loginUserDto.password, passwordAuth.encryptedPassword)) { "invalid_credentials" }

		val sessionId = UUID.randomUUID()

		val access = issueAccess(
			userId = user.id,
			email = user.email,
			userRole = user.userRole.name,
			companyId = null, // Set a real companyId here if/when you have that relation
			sessionId = sessionId
		) ?: throw Exception("failed_to_login")

		val refresh = issueRefresh(user.id, sessionId)
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

		val createdRefreshToken = refreshTokenService.create(refreshTokenToCreate)
			?: throw Exception("failed_to_login")

		Token(refresh, access)
	}
	override suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): User? = dbQuery {
		require(registerCompanyDto.email.isNotBlank())
		require(registerCompanyDto.password.length >= 8)
		require(registerCompanyDto.password == registerCompanyDto.confirmPassword) { "password_mismatch" }
		require(registerCompanyDto.name.isNotBlank())
		require(registerCompanyDto.industry.isNotBlank())

		val existing = userService.getByEmail(registerCompanyDto.email)
		require(existing == null) { "email_taken" }

		val encryptedPassword = BCrypt.hashpw(registerCompanyDto.password, BCrypt.gensalt())

		val userToCreate = User.createNew(
			email = registerCompanyDto.email,
			firstName = registerCompanyDto.name,
			lastName = "",
			userRole = UserRole.company
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

		val now = Instant.now()
		val companyToCreate = com.internconnect.model.company.Company(
			id = UUID.randomUUID(),
			name = registerCompanyDto.name,
			industry = registerCompanyDto.industry,
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

		createdUser
	}

	override suspend fun logout(user: User) {
		TODO("Not yet implemented")
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
		sessionId: UUID
	): String? {
		val now = Instant.now()
		val nowPlusExp = now.plus(jwtConfig.refreshTtl)
		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("sid", sessionId.toString())
			.withClaim("typ", "refresh")
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
}