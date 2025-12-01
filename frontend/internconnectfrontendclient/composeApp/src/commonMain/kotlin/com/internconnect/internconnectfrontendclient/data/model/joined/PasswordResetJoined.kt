package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetJoined(
	val id: String,
	val user: UserJoined,
	val codeHash: String,
	val expiresAt: String,
	val consumedAt: String?,
	val createdAt: String,
	val updatedAt: String
)