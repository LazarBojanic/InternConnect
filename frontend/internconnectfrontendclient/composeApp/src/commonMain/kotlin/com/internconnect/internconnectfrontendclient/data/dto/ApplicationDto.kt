package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationDto(
	val internshipId: String,
	val position: String,
	val company: String,
	val country: String,
	val city: String,
	val status: ApplicationStatus,
)
enum class ApplicationStatus { APPLIED, REJECTED, ACCEPTED }
