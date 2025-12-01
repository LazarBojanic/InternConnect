package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class PasswordAuthJoined(
	val user: UserJoined,
	val encryptedPassword: String,
	val encryptionAlgorithm: String,
	val passwordSetAt: String,
	val createdAt: String,
	val updatedAt: String
)