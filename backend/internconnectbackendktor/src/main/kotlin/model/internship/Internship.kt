package com.internconnect.model.internship

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Internship(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyId: UUID,
	val title: String,
	val category: String,
	val description: String,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)