package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class SessionJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val user: UserJoined,
	val ip: String?,
	val userAgent: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)