package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class CompanyJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val name: String,
	val industry: String,
	val website: String?,
	val logoUrl: String?,
	val country: String?,
	val city: String?,
	val about: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)
