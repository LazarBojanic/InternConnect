package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class StudentJoined(
	val user: UserJoined,
	val schoolName: String,
	val grade: Int,
	val bio: String?,
	val interests: String?,
	val avatarUrl: String?,
	val createdAt: String,
	val updatedAt: String
)