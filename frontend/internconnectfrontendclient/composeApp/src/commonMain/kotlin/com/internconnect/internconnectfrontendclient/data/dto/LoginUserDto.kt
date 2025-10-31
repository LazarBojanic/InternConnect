package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDto(
	val email: String,
	val password: String,
	val userAgent: String,
	val ip: String
)