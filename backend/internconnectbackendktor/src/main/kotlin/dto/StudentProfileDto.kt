package com.internconnect.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudentProfileDto(
	val userId: String,
	val email: String,
	val firstName: String,
	val lastName: String,
	val fullName: String,
	val userRole: String,
	val isEmailVerified: Boolean,
	val userStatus: String,
	val schoolName: String,
	val grade: Int,
	val bio: String?,
	val interests: String?,
	val avatarUrl: String?
)