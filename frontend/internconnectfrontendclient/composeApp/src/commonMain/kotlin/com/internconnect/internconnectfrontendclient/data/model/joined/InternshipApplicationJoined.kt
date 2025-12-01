package com.internconnect.internconnectfrontendclient.data.model.joined

import kotlinx.serialization.Serializable

@Serializable
data class InternshipApplicationJoined(
	val id: String,
	val internship: InternshipJoined,
	val student: StudentJoined,
	val status: InternshipApplicationStatus,
	val resume: String,
	val interviewNotes: String?,
	val createdAt: String,
	val updatedAt: String
)
enum class InternshipApplicationStatus {
	APPLIED, REJECTED, ACCEPTED
}