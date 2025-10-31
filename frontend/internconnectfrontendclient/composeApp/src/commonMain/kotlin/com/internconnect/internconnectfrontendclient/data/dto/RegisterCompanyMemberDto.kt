package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCompanyMemberDto(
	val email: String,
	val password: String,
	val confirmPassword: String,
	val name: String,
	val industry: String,
)