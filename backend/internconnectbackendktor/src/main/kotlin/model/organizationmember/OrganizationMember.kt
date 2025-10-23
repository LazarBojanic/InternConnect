package com.internconnect.model.organizationmember

import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class OrganizationMember(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val organizationID: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)