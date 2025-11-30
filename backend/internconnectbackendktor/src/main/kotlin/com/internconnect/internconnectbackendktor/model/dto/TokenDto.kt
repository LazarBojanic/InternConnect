package com.internconnect.internconnectbackendktor.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
	val access: String?,
	val refresh: String?
)