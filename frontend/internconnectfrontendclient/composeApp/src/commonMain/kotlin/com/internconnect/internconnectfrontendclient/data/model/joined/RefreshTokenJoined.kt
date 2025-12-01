package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenJoined(
	val id: String,
	val user: UserJoined,
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