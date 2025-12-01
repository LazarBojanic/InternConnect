package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class OAuthAccountJoined(
	val id: String,
	val user: UserJoined,
	val provider: String,
	val providerUserId: String,
	val providerEmail: String?,
	val encryptedAccessToken: String?,
	val encryptedRefreshToken: String?,
	val tokenExpiresAt: String,
	val createdAt: String,
	val updatedAt: String
)