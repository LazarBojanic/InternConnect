package com.internconnect.service.implementation

import com.auth0.jwt.*
import com.internconnect.auth.JwtConfig
import com.internconnect.dto.LoginUserDto
import com.internconnect.model.dto.RegisterStudentDto
import com.internconnect.model.user.User
import com.internconnect.model.user.UserRole
import com.internconnect.service.specification.*
import org.mindrot.jbcrypt.BCrypt
import java.time.Instant
import java.util.*


class AuthService(
	private val userService: IUserService,
	private val studentService: IStudentService,
	private val companyService: ICompanyService,
	private val refreshTokenService: IRefreshTokenService,
	private val jwtConfig: JwtConfig
) : IAuthService {

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User? {
		require(registerStudentDto.email.isNotBlank())
		require(registerStudentDto.password.length >= 8)
		val existing = userService.getByEmail(registerStudentDto.email)
		require(existing == null) { "email_taken" }
		val encryptedPassword = BCrypt.hashpw(registerStudentDto.password, BCrypt.gensalt())
		val toSave = User.createNew(
			email = registerStudentDto.email,
			firstName = registerStudentDto.firstName,
			lastName = registerStudentDto.lastName,
			userRole = UserRole.student
		)
		return userService.create(toSave)
	}

	override fun login(loginUserDto: LoginUserDto): User? {
		TODO("Not yet implemented")
	}

	override fun logout(user: User) {
		TODO("Not yet implemented")
	}

	override fun issueAccess(
		userId: UUID,
		email: String,
		role: String,
		orgId: UUID?
	): String {
		val now = Instant.now()
		val nowPlusExp = now.plus(jwtConfig.accessTtl)
		return JWT.create()
			.withIssuer(jwtConfig.iss)
			.withAudience(jwtConfig.aud)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("role", role)
			.withClaim("orgId", orgId?.toString())
			.withIssuedAt(now)
			.withExpiresAt(nowPlusExp)
			.sign(jwtConfig.alg)
	}

	override fun issueRefresh(
		sessionId: UUID,
		userId: UUID
	): String {
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
			.build()
	}

}