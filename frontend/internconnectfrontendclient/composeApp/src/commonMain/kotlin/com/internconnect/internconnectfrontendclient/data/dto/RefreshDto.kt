package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshDto(
	val refresh: String?,
	val userAgent: String?,
	val ip: String?
)