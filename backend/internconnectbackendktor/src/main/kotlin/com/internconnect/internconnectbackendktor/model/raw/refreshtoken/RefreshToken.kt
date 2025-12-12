package com.internconnect.internconnectbackendktor.model.raw.refreshtoken

import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class RefreshToken(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val sessionId: UUID,
	val hash: String,
	@Serializable(with = InstantSerializer::class)
	val issuedAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val revokedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
) {
	companion object {
		fun createNew(
			sessionId: UUID,
			hash: String,
			issuedAt: Instant,
			expiresAt: Instant,
			revokedAt: Instant?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null

		): RefreshToken {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return RefreshToken(
				id = UUID.randomUUID(),
				sessionId = sessionId,
				hash = hash,
				issuedAt = issuedAt,
				expiresAt = expiresAt,
				revokedAt = revokedAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}