package com.internconnect.model.refreshtoken

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class RefreshToken(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val sessionId: UUID,
	val hash: String,
	@Serializable(with = InstantSerializer::class)
	val issuedAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val revokedAt: Instant?,
	val userAgent: String?,
	val ip: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			userId: UUID,
			sessionId: UUID,
			hash: String,
			issuedAt: Instant,
			expiresAt: Instant,
			revokedAt: Instant?,
			userAgent: String?,
			ip: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): RefreshToken {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return RefreshToken(
				id = UUID.randomUUID(),
				userId = userId,
				sessionId = sessionId,
				hash = hash,
				issuedAt = issuedAt,
				expiresAt = expiresAt,
				revokedAt = revokedAt,
				userAgent = userAgent,
				ip = ip,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}