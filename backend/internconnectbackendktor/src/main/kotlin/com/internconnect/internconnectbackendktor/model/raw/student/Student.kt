package com.internconnect.internconnectbackendktor.model.raw.student

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class Student(
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val schoolName: String,
	val grade: Int,
	val bio: String?,
	val interests: String?,
	val avatarUrl: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
) {
	companion object {
		fun createNew(
			userId: UUID,
			schoolName: String,
			grade: Int,
			bio: String?,
			interests: String?,
			avatarUrl: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): Student {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return Student(
				userId = userId,
				schoolName = schoolName,
				grade = grade,
				bio = bio,
				interests = interests,
				avatarUrl = avatarUrl,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}