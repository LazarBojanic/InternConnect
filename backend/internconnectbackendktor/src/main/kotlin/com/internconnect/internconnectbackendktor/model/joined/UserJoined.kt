package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.model.raw.user.UserStatus
import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class UserJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val email: String,
	val firstName: String,
	val lastName: String,
	val role: UserRole,
	val isEmailVerified: Boolean,
	val status: UserStatus,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)