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
){
	companion object {
		fun createNew(
			email: String,
			firstName: String,
			lastName: String,
			userRole: UserRole
		): User {
			val now = Instant.now()
			return User(
				id = UUID.randomUUID(),
				email = email,
				fullName = "$firstName $lastName",
				userRole = userRole,
				isEmailVerified = false,
				userStatus = UserStatus.active,
				createdAt = now,
				updatedAt = now
			)
		}
	}
}


enum class UserRole {
	student, company, admin
}

enum class UserStatus {
	active, suspended, deleted
}