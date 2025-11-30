package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class RefreshTokenJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val user: UserJoined,
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
)