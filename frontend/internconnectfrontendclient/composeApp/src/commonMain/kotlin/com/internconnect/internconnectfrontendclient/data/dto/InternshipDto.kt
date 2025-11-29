package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class InternshipDto(
	val id: String,
	val position: String,
	val company: String,
	val country: String,
	val city: String,
	val description: String,
	val category: String,
)