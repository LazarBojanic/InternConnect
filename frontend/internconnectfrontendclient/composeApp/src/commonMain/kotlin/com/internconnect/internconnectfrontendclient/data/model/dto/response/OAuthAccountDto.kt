package com.internconnect.internconnectfrontendclient.data.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class OAuthAccountDto(
	val id: String,
	val user: UserDto,
	val provider: String,
	val providerUserId: String,
	val providerEmail: String?,
	val encryptedAccessToken: String?,
	val encryptedRefreshToken: String?,
	val tokenExpiresAt: String,
	val createdAt: String,
	val updatedAt: String
)