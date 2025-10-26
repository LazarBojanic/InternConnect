package com.internconnect.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCompanyDto(
	val email: String,
	val password: String,
	val confirmPassword: String,
	val name: String,
	val industry: String,
)