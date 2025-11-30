package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
	val access: String?,
	val refresh: String?
)