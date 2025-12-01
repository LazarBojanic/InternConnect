package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class AuditLogJoined(
	val id: String,
	val user: UserJoined,
	val action: String,
	val metadata: String?,
	val ip: String?,
	val createdAt: String,
	val updatedAt: String
)