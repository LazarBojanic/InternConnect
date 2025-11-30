package com.internconnect.internconnectbackendktor.model.raw.internshipapplication

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
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
){
	companion object{
		fun createNew(
			internshipId: UUID,
			studentId: UUID,
			status: InternshipApplicationStatus,
			resume: ExposedBlob,
			interviewNotes: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): InternshipApplication{
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return InternshipApplication(
				id = UUID.randomUUID(),
				internshipId = internshipId,
				studentId = studentId,
				status = status,
				resume = resume,
				interviewNotes = interviewNotes,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}

enum class InternshipApplicationStatus {
	PENDING, APPROVED, REJECTED
}