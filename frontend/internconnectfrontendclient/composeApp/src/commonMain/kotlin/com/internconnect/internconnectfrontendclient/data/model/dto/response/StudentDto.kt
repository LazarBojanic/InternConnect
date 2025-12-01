package com.internconnect.internconnectfrontendclient.data.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class StudentDto(
	val user: UserDto,
	val schoolName: String,
	val grade: Int,
	val bio: String?,
	val interests: String?,
	val avatarUrl: String?,
	val createdAt: String,
	val updatedAt: String
)