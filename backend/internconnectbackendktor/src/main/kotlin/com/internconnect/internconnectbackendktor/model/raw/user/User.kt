package com.internconnect.internconnectbackendktor.model.raw.user

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class User(
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
) {
	companion object {
		fun createNew(
			email: String,
			firstName: String,
			lastName: String,
			role: UserRole,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): User {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return User(
				id = UUID.randomUUID(),
				email = email,
				firstName = firstName,
				lastName = lastName,
				role = role,
				isEmailVerified = false,
				status = UserStatus.ACTIVE,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}


enum class UserRole {
	STUDENT, COMPANY_MEMBER, ADMIN
}

enum class UserStatus {
	ACTIVE, SUSPENDED, DELETED
}