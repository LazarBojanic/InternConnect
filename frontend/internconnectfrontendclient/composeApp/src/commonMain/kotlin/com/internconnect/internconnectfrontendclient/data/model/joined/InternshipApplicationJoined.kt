package com.internconnect.internconnectfrontendclient.data.model.joined

import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus
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
