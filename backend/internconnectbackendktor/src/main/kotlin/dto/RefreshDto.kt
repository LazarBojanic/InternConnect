package com.internconnect.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshDto(
	val refresh: String,
	val userAgent: String?,
	val ip: String?
)