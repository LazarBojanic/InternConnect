package com.internconnect.model.studentprofile

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Student (
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val firstName: String,
	val lastName: String,
	val schoolName: String,
	val grade: Int,
	val bio: String?,
	val interests: String?,
	val avatarUrl: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)