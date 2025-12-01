package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class InternshipJoined(
	val id: String,
	val company: CompanyJoined,
	val title: String,
	val category: String,
	val description: String,
	val createdAt: String,
	val updatedAt: String
)
