package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class OAuthAccountJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val user: UserJoined,
	val provider: String,
	@Serializable(with = UUIDSerializer::class)
	val providerUserId: UUID,
	val providerEmail: String?,
	val encryptedAccessToken: String?,
	val encryptedRefreshToken: String?,
	@Serializable(with = InstantSerializer::class)
	val tokenExpiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)