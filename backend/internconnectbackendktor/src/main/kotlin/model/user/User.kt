package com.internconnect.model.user


import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class User(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val email: String,
	val fullName: String,
	val userRole: UserRole,
	val isEmailVerified: Boolean,
	val userStatus: UserStatus,
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
			userRole: UserRole,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): User {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return User(
				id = UUID.randomUUID(),
				email = email,
				fullName = "$firstName $lastName",
				userRole = userRole,
				isEmailVerified = false,
				userStatus = UserStatus.ACTIVE,
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