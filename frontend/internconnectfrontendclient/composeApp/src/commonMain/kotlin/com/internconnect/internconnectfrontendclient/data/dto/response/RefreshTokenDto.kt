package com.internconnect.internconnectfrontendclient.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDto(
	val id: String,
	val user: UserDto,
	val sessionId: String,
	val hash: String,
	val issuedAt: String,
	val expiresAt: String,
	val revokedAt: String?,
	val userAgent: String?,
	val ip: String?,
	val createdAt: String,
	val updatedAt: String
)