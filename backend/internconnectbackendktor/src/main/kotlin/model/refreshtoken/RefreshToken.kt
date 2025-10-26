package com.internconnect.model.refreshtoken

import com.internconnect.util.InetAddressSerializer
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.net.InetAddress
import java.time.Instant
import java.util.UUID
@Serializable
data class RefreshToken(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val tokenHash: String,
	@Serializable(with = InstantSerializer::class)
	val issuedAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val revokedAt: Instant?,
	val userAgent: String,
	@Serializable(with = InetAddressSerializer::class)
	val ip: InetAddress,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			userId: UUID,
			tokenHash: String,
			issuedAt: Instant,
			expiresAt: Instant,
			revokedAt: Instant?,
			userAgent: String,
			ip: InetAddress,
		): RefreshToken {
			return RefreshToken(
				id = UUID.randomUUID(),
				userId = userId,
				tokenHash = tokenHash,
				issuedAt = issuedAt,
				expiresAt = expiresAt,
				revokedAt = revokedAt,
				userAgent = userAgent,
				ip = ip,
				createdAt = Instant.now(),
				updatedAt = Instant.now()
			)
		}
	}
}