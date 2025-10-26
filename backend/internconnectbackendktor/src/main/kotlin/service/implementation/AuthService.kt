package com.internconnect.service.implementation

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.auth.JwtConfig
import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.Token
import com.internconnect.model.dto.RegisterCompanyDto
import com.internconnect.model.dto.RegisterStudentDto
import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.studentprofile.Student
import com.internconnect.model.user.User
import com.internconnect.model.user.UserRole
import com.internconnect.service.specification.*
import org.mindrot.jbcrypt.BCrypt
import java.net.InetAddress
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

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User? {
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
		if(createdUser != null){
			val passwordAuthToCreate = PasswordAuth.createNew(
				userId = createdUser.id,
				encryptedPassword = encryptedPassword,
				encryptionAlgorithm = "bcrypt"
			)
			val createdPasswordAuth = passwordAuthService.create(passwordAuthToCreate)
			if(createdPasswordAuth != null){
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
				val createdStudent = studentService.create(studentToCreate)
				if(createdStudent != null){
					return createdUser
				}
			}
		}
		throw Exception("failed_to_register")
	}

	override suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): User? {
		TODO("Not yet implemented")
	}

	override suspend fun login(loginUserDto: LoginUserDto): Token? {
		val user = userService.getByEmail(loginUserDto.email)
		if(user != null){
			val passwordAuth = passwordAuthService.getByUserId(user.id)
			if(passwordAuth != null){
				if(BCrypt.checkpw(loginUserDto.password, passwordAuth.encryptedPassword)){
					val sessionId = UUID.randomUUID()
					val access = issueAccess(
						userId = user.id,
						email = user.email,
						userRole = user.userRole.name,
						companyId = null,
						sessionId
					)
					if(access != null){
						val refresh = issueRefresh(user.id, sessionId)
						if(refresh != null){
							val refreshHash = sha256(refresh)
							if(refreshHash != null){
								val parsedRefresh = JWT.decode(refresh)
								val refreshTokenToCreate = RefreshToken.createNew(
									userId = user.id,
									sessionId = sessionId,
									hash = refreshHash,
									issuedAt = parsedRefresh.issuedAt.toInstant(),
									expiresAt = parsedRefresh.expiresAt.toInstant(),
									revokedAt = null,
									userAgent = loginUserDto.userAgent,
									ip = InetAddress.getByName(loginUserDto.ip)
								)
								val createdRefreshToken = refreshTokenService.create(refreshTokenToCreate)
								if(createdRefreshToken != null){
									return Token(user.id, sessionId, refresh, access)
								}
							}
						}
					}
				}
			}
		}
		throw Exception("invalid_credentials")
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
			.build()
	}
	override fun sha256(input: String): String? {
		return MessageDigest.getInstance("SHA-256")
			.digest(input.toByteArray())
			.fold("") { str, byte -> str + "%02x".format(byte) }
	}
}