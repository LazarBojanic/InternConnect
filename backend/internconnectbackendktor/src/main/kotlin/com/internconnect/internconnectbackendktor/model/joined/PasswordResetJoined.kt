package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class PasswordResetJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val user: UserJoined,
	val codeHash: String,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val consumedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)