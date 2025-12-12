package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable


@Serializable
data class CompanyMemberJoined(
	val user: UserJoined,
	val company: CompanyJoined,
	val role: String,
	val status: String,
	val joinedAt: String?,
	val createdAt: String,
	val updatedAt: String
)