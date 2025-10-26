package com.internconnect.model.oauthaccount

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID
@Serializable
data class OAuthAccount(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val provider: String = "google",
	@Serializable(with = UUIDSerializer::class)
	val providerUserID: UUID,
	val providerEmail: String?,
	val encryptedAccessToken: String?,
	val encryptedRefreshToken: String?,
	@Serializable(with = InstantSerializer::class)
	val tokenExpiresAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)