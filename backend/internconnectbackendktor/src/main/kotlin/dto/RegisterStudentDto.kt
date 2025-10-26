package com.internconnect.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterStudentDto(
	val email: String,
	val password: String,
	val confirmPassword: String,
	val firstName: String,
	val lastName: String,
	val schoolName: String,
	val grade: Int
)