package com.internconnect.model.organizationinvitation

import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class OrganizationInvitation(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val organizationID: UUID,
	val email: String,
	val codeHash: String,
	val expiresAt: LocalDateTime,
	@Serializable(with = UUIDSerializer::class)
	val invitedBy: UUID,
	@Serializable(with = UUIDSerializer::class)
	val acceptedBy: UUID?,
	val acceptedAt: LocalDateTime?,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)