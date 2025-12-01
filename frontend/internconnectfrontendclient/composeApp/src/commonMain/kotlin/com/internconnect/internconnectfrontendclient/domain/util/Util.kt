package com.internconnect.internconnectfrontendclient.domain.util

import com.internconnect.internconnectfrontendclient.data.model.joined.CompanyJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.StudentJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.UserJoined
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import okio.ByteString.Companion.decodeBase64

fun jwtDecode(token: String): JsonObject {
	val payload = token.split(".")[1]
	val decoded = payload.decodeBase64()!!.utf8()
	return Json.parseToJsonElement(decoded).jsonObject
}

object DummyData {

	fun categories(): List<String> {
		return listOf("Android", "iOS", "Web", "Backend")
	}

	fun internships(): List<InternshipJoined> {
		return listOf(
			internship1,
			internship2,
			internship3,
			internship4,
			internship5
		)
	}

	fun savedInternships(): List<InternshipJoined> {
		return listOf(
			internship3,
			internship4,
		)
	}

	fun internshipApplications(): List<InternshipApplicationJoined> {
		return listOf(
			application1,
			application2,
			application3,
			application4
		)
	}

	// ------------------------------------------------------------
	// Dummy Internship Data
	// ------------------------------------------------------------

	private val companyA = CompanyJoined(
		id = "comp-001",
		name = "TechNova",
		industry = "Software",
		website = "https://technova.dev",
		logoUrl = null,
		country = "USA",
		city = "San Francisco",
		about = "A modern tech company focusing on mobile and web technologies.",
		createdAt = "2024-01-01T10:00:00Z",
		updatedAt = "2024-01-01T10:00:00Z"
	)

	private val companyB = CompanyJoined(
		id = "comp-002",
		name = "AppForge",
		industry = "Mobile Development",
		website = "https://appforge.io",
		logoUrl = null,
		country = "Canada",
		city = "Toronto",
		about = "Building next-gen mobile products for startups.",
		createdAt = "2024-02-10T12:00:00Z",
		updatedAt = "2024-02-10T12:00:00Z"
	)

	private val companyC = CompanyJoined(
		id = "comp-003",
		name = "CloudSpark",
		industry = "Cloud Services",
		website = "https://cloudspark.com",
		logoUrl = null,
		country = "UK",
		city = "London",
		about = "Cloud infrastructure and backend engineering specialists.",
		createdAt = "2024-03-05T09:30:00Z",
		updatedAt = "2024-03-05T09:30:00Z"
	)

	val internship1 = InternshipJoined(
		id = "int-001",
		company = companyA,
		title = "Android Developer Intern",
		category = "Android",
		description = "Work with Kotlin and Jetpack Compose to build modern Android apps.",
		createdAt = "2024-04-01T09:00:00Z",
		updatedAt = "2024-04-01T09:00:00Z"
	)

	val internship2 = InternshipJoined(
		id = "int-002",
		company = companyA,
		title = "iOS Developer Intern",
		category = "iOS",
		description = "Assist in developing SwiftUI-based iOS applications.",
		createdAt = "2024-04-03T11:00:00Z",
		updatedAt = "2024-04-03T11:00:00Z"
	)

	val internship3 = InternshipJoined(
		id = "int-003",
		company = companyB,
		title = "Web Developer Intern",
		category = "Web",
		description = "Contribute to frontend development with React and TypeScript.",
		createdAt = "2024-04-05T08:30:00Z",
		updatedAt = "2024-04-05T08:30:00Z"
	)

	val internship4 = InternshipJoined(
		id = "int-004",
		company = companyC,
		title = "Backend Engineering Intern",
		category = "Backend",
		description = "Help build scalable backend systems using Node.js and Kotlin.",
		createdAt = "2024-04-07T14:00:00Z",
		updatedAt = "2024-04-07T14:00:00Z"
	)

	val internship5 = InternshipJoined(
		id = "int-005",
		company = companyB,
		title = "Junior QA Intern",
		category = "Web",
		description = "Test and validate application quality using automation tools.",
		createdAt = "2024-04-10T16:00:00Z",
		updatedAt = "2024-04-10T16:00:00Z"
	)

	// ------------------------------------------------------------
	// Dummy Student Data
	// ------------------------------------------------------------

	private val studentUser1 = UserJoined(
		id = "user-001",
		email = "john.doe@example.com",
		firstName = "John",
		lastName = "Doe",
		role = "STUDENT",
		isEmailVerified = true,
		status = "ACTIVE",
		createdAt = "2024-01-15T09:00:00Z",
		updatedAt = "2024-01-15T09:00:00Z"
	)

	private val student1 = StudentJoined(
		user = studentUser1,
		schoolName = "Tech University",
		grade = 12,
		bio = "Aspiring mobile developer.",
		interests = "Android, Kotlin, UI/UX",
		avatarUrl = null,
		createdAt = "2024-01-15T09:00:00Z",
		updatedAt = "2024-01-15T09:00:00Z"
	)

	// ------------------------------------------------------------
	// Dummy Internship Applications
	// ------------------------------------------------------------

	val application1 = InternshipApplicationJoined(
		id = "app-001",
		internship = internship1,
		student = student1,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_john_doe.pdf",
		interviewNotes = null,
		createdAt = "2024-04-02T10:00:00Z",
		updatedAt = "2024-04-02T10:00:00Z"
	)

	val application2 = InternshipApplicationJoined(
		id = "app-002",
		internship = internship2,
		student = student1,
		status = InternshipApplicationStatus.REJECTED,
		resume = "resume_john_doe.pdf",
		interviewNotes = "Needs stronger Swift fundamentals.",
		createdAt = "2024-04-04T14:00:00Z",
		updatedAt = "2024-04-04T14:00:00Z"
	)

	val application3 = InternshipApplicationJoined(
		id = "app-003",
		internship = internship3,
		student = student1,
		status = InternshipApplicationStatus.ACCEPTED,
		resume = "resume_john_doe.pdf",
		interviewNotes = "Great portfolio. Good React knowledge.",
		createdAt = "2024-04-06T12:00:00Z",
		updatedAt = "2024-04-06T12:00:00Z"
	)

	val application4 = InternshipApplicationJoined(
		id = "app-004",
		internship = internship4,
		student = student1,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_john_doe.pdf",
		interviewNotes = null,
		createdAt = "2024-04-08T09:30:00Z",
		updatedAt = "2024-04-08T09:30:00Z"
	)
}
