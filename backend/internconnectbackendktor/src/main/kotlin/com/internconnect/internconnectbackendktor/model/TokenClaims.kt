package com.internconnect.internconnectbackendktor.model

import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class TokenClaims(
	val type: TokenType,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val sessionId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyId: UUID?,
	val email: String,
	val role: UserRole,
	val ip: String?,
	val userAgent: String?,
	@Serializable(with = InstantSerializer::class)
	val issuedAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val revokedAt: Instant?
)
enum class TokenType{
	ACCESS, REFRESH
}