package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class UserJoined(
	val id: String,
	val email: String,
	val firstName: String,
	val lastName: String,
	val role: String,
	val isEmailVerified: Boolean,
	val status: String,
	val createdAt: String,
	val updatedAt: String
)