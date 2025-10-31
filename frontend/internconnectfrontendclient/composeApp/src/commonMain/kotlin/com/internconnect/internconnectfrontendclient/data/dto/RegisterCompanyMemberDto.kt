package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCompanyMemberDto(
	val userEmail: String,
	val userFirstName: String,
	val userLastName: String,
	val password: String,
	val confirmPassword: String,
	val companyName: String,
	val companyIndustry: String,
)