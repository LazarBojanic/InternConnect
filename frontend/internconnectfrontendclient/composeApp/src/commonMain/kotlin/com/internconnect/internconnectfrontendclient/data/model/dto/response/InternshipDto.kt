package com.internconnect.internconnectfrontendclient.data.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class InternshipDto(
	val id: String,
	val company: CompanyDto,
	val title: String,
	val category: String,
	val description: String,
	val createdAt: String,
	val updatedAt: String
)