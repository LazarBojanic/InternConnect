package com.internconnect.model.company

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID
@Serializable
data class Company(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val name: String,
	val industry: String,
	val website: String?,
	val logoUrl: String?,
	val hqCountry: String?,
	val city: String?,
	val about: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)