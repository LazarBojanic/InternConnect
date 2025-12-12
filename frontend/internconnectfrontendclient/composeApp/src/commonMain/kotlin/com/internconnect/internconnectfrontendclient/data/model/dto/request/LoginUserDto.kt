package com.internconnect.internconnectfrontendclient.data.model.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDto(
	val email: String,
	val password: String,
	val userAgent: String,
	val ip: String
)