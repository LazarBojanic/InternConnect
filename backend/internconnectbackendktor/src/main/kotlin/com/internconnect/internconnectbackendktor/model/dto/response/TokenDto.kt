package com.internconnect.internconnectbackendktor.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
	val access: String?,
	val refresh: String?
)