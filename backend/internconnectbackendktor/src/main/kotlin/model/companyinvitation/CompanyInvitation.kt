package com.internconnect.model.companyinvitation

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class CompanyInvitation(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyID: UUID,
	val email: String,
	val codeHash: String,
	val expiresAt: LocalDateTime,
	@Serializable(with = UUIDSerializer::class)
	val invitedBy: UUID,
	@Serializable(with = UUIDSerializer::class)
	val acceptedBy: UUID?,
	@Serializable(with = InstantSerializer::class)
	val acceptedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)