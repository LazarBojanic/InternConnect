package com.internconnect.model.user

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class UserDto(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val email: String,
	val password: String,
	val fullName: String,
	val role: Role,
)