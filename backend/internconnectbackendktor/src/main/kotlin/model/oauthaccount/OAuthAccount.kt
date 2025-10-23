package com.internconnect.model.oauthaccount

import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class OAuthAccount(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val provider: String = "google",
	@Serializable(with = UUIDSerializer::class)
	val providerUserID: UUID,
	val providerEmail: String?,
	val encryptedAccessToken: String?,
	val encryptedRefreshToken: String?,
	val tokenExpiresAt: LocalDateTime?,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)