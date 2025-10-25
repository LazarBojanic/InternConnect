package com.internconnect.service.implementation

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.Algorithm
import com.internconnect.model.user.User
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.service.specification.IAuthService
import java.util.*


class AuthService (
	private val userRepository: UserRepository,
) : IAuthService {
	val issuer = "test"
	val audience = "test"
	val verifier = "test"
	val secret = "test"
	val algorithm = Algorithm.HMAC256(secret)

	override fun register(user: User): User? {
		TODO("Not yet implemented")
	}

	override fun login(user: User): User? {
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
	) : String{

		return JWT.create()
			.withIssuer(issuer)
			.withAudience(audience)
			.withSubject(userId.toString())
			.withClaim("email", email)
			.withClaim("role", role)
			.withClaim("orgId", orgId?.toString())
			.withIssuedAt(java.util.Date())
			.withExpiresAt(java.util.Date(System.currentTimeMillis() + 1000_00 * 60_000))
			.sign(algorithm)
	}

	override fun issueRefresh(
		sessionId: UUID,
		userId: UUID
	): Pair<String, Date> {
		val expires = Date(System.currentTimeMillis() + 1000_00 * 60_000)

		val token = JWT.create()
			.withIssuer(issuer)
			.withAudience(audience)
			.withSubject(userId.toString())
			.withClaim("sid", sessionId.toString())
			.withClaim("typ", "refresh")
			.withExpiresAt(expires)
			.sign(algorithm)

		return token to expires
	}

	override fun verifier(): JWTVerifier {
		return JWT.require(algorithm)
			.withIssuer(issuer)
			.withAudience(audience)
			.build()
	}

}