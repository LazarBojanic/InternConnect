package com.internconnect.internconnectbackendktor.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class InternshipApplicationDto(
	val id: String,
	val internship: InternshipDto,
	val student: StudentDto,
	val status: String,
	val resume: String,
	val interviewNotes: String?,
	val createdAt: String,
	val updatedAt: String

)