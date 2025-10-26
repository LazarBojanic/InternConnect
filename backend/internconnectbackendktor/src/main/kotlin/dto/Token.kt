package com.internconnect.dto

import kotlinx.serialization.Serializable

@Serializable
data class Token(
	val access: String,
	val refresh: String
)