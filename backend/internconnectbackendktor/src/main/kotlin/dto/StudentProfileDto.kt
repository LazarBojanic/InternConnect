package com.internconnect.dto

import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

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