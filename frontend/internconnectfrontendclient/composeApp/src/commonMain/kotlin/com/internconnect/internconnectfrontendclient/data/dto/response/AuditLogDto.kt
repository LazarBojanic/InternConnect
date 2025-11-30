package com.internconnect.internconnectfrontendclient.data.dto.response

import com.internconnect.internconnectbackendktor.model.dto.response.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class AuditLogDto(
	val id: String,
	val user: UserDto,
	val action: String,
	val metadata: String?,
	val ip: String?,
	val createdAt: String,
	val updatedAt: String
)