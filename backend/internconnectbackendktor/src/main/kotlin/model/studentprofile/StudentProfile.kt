package com.internconnect.model.studentprofile

import java.util.UUID

//TODO implement
data class StudentProfile (
	val id: UUID,
	val name: String,
	val email: String,
	val phone: String,
	val address: String,
	val gender: String,
	val dob: String,
	val avatar: String
)