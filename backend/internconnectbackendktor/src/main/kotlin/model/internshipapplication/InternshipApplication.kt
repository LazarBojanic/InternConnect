package com.internconnect.model.internshipapplication

import com.internconnect.util.ExposedBlobSerializer
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import java.time.Instant
import java.util.UUID
@Serializable
data class InternshipApplication(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val internshipId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val studentId: UUID,
	val status: InternshipApplicationStatus,
	@Serializable(with = ExposedBlobSerializer::class)
	val resume: ExposedBlob,
	val interviewNotes: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)

enum class InternshipApplicationStatus {
	PENDING, APPROVED, REJECTED
}