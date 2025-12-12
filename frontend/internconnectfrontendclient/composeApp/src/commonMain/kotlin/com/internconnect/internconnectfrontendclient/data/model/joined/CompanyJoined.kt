package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable


@Serializable
data class CompanyJoined(
	val id: String,
	val name: String,
	val industry: String,
	val website: String?,
	val logoUrl: String?,
	val country: String?,
	val city: String?,
	val about: String?,
	val createdAt: String,
	val updatedAt: String
)
