package com.internconnect.model.organization

import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class Organization(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val name: String,
	val industry: String,
	val website: String?,
	val logoUrl: String?,
	val hqCountry: String?,
	val city: String?,
	val about: String?,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)