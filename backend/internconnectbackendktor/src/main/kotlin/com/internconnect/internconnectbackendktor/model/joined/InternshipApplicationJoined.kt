package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationStatus
import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import java.time.Instant
import java.util.UUID

@Serializable
data class InternshipApplicationJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val internship: InternshipJoined,
	val student: StudentJoined,
	val status: InternshipApplicationStatus,
	@Serializable(with = ExposedBlobSerializer::class)
	val resume: ExposedBlob,
	val interviewNotes: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant

)