package com.internconnect.internconnectfrontendclient.data.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class EmailVerificationDto(
	val id: String,
	val user: UserDto,
	val codeHash: String,
	val sentToEmail: String,
	val expiresAt: String,
	val consumedAt: String?,
	val createdAt: String,
	val updatedAt: String
)
