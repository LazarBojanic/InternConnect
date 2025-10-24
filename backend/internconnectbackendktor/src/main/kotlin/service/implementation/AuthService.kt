package com.internconnect.service.implementation

import com.internconnect.model.user.User
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.service.specification.IAuthService
import io.ktor.server.application.Application
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant


class AuthService (
	private val userRepository: UserRepository,
) : IAuthService {
	val issuer = "test"
	val audience = "test"
	val verifier = "test"
	val secret = "test"
	val algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret)

	override suspend fun register(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun login(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun logout(user: User) {
		TODO("Not yet implemented")
	}

	override suspend fun issueAccess(
		userId: UUID,
		email: String,
		role: String,
		orgId: UUID?
	) : String{

		return com.auth0.jwt.JWT.create()
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

	@OptIn(ExperimentalTime::class)
	override suspend fun issueRefresh(
		sessionId: UUID,
		userId: UUID
	): Pair<String, LocalDateTime> {
		val exp = Clock.System.now()

		val token = com.auth0.jwt.JWT.create()
			.withIssuer(issuer)
			.withAudience(audience)
			.withSubject(userId.toString())
			.withClaim("sid", sessionId.toString())
			.withClaim("typ", "refresh")
			.withExpiresAt(exp.toJavaInstant().let { java.util.Date.from(it) })
			.sign(algorithm)

		return null
	}

}