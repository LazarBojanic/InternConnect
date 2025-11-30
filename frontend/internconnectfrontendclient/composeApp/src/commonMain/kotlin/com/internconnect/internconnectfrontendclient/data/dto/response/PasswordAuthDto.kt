package com.internconnect.internconnectfrontendclient.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class PasswordAuthDto(
	val user: UserDto,
	val encryptedPassword: String,
	val encryptionAlgorithm: String,
	val passwordSetAt: String,
	val createdAt: String,
	val updatedAt: String
)