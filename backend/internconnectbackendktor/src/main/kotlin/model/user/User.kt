package com.internconnect.model.user


import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
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
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime

)
enum class Role {
	student, organization, admin
}

enum class Status {
	active, suspended, deleted
}