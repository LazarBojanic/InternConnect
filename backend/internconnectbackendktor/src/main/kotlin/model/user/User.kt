package com.internconnect.model.user


import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class User(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val email: String,
	val fullName: String,
	val role: Role,
	val isEmailVerified: Boolean,
	val status: Status,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)
enum class Role {
	student, organization, admin
}

enum class Status {
	active, suspended, deleted
}