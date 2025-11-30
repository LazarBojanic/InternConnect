package com.internconnect.internconnectbackendktor.model.raw.oauthaccount

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class OAuthAccount(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
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
){
	companion object{
		fun createNew(
			userId: UUID,
			provider: String,
			providerUserID: UUID,
			providerEmail: String?,
			encryptedAccessToken: String?,
			encryptedRefreshToken: String?,
			tokenExpiresAt: Instant,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): OAuthAccount{
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return OAuthAccount(
				id = UUID.randomUUID(),
				userId = userId,
				provider = provider,
				providerUserId = providerUserID,
				providerEmail = providerEmail,
				encryptedAccessToken = encryptedAccessToken,
				encryptedRefreshToken = encryptedRefreshToken,
				tokenExpiresAt = tokenExpiresAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}