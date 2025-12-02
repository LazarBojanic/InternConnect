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
		return listOf(
			"Android", "iOS", "Web", "Backend", "Data Science", "DevOps", "Security", "Design"
		)
	}

	fun internships(): List<InternshipJoined> {
		return listOf(
			internship1, internship2, internship3, internship4, internship5,
			internship6, internship7, internship8, internship9, internship10
		)
	}

	fun savedInternships(): List<InternshipJoined> {
		return listOf(
			internship3,
			internship4,
			internship6,
			internship9,
		)
	}

	fun internshipApplications(): List<InternshipApplicationJoined> {
		// Ensure comp-001 (companyA) has a healthy spread of statuses and enough volume to light up all pads
		return listOf(
			// For companyA (comp-001): internships 1,2,6,7
			application1_applied_int1_john,
			application2_rejected_int2_john,
			application3_accepted_int3_john, // other company to keep data varied
			application4_applied_int4_john,   // other company
			application5_accepted_int1_jane,
			application6_applied_int2_jane,
			application7_rejected_int6_jane,
			application8_applied_int6_sam,
			application9_accepted_int7_sam,
			application10_applied_int7_john,
			application11_rejected_int2_sam,
			application12_applied_int1_sam,
			// More for other companies
			application13_applied_int8_jane,
			application14_accepted_int9_sam,
			application15_rejected_int10_john,
		)
	}

	// ------------------------------------------------------------
	// Dummy Companies
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

	// ------------------------------------------------------------
	// Dummy Internships
	// ------------------------------------------------------------

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

	// Additional postings to ensure comp-001 has multiple postings
	val internship6 = InternshipJoined(
		id = "int-006",
		company = companyA,
		title = "DevOps Intern",
		category = "DevOps",
		description = "Work with CI/CD pipelines, Docker, and cloud tooling.",
		createdAt = "2024-04-12T09:00:00Z",
		updatedAt = "2024-04-12T09:00:00Z"
	)

	val internship7 = InternshipJoined(
		id = "int-007",
		company = companyA,
		title = "UI/UX Design Intern",
		category = "Design",
		description = "Create wireframes and prototypes for mobile and web apps.",
		createdAt = "2024-04-13T09:00:00Z",
		updatedAt = "2024-04-13T09:00:00Z"
	)

	val internship8 = InternshipJoined(
		id = "int-008",
		company = companyC,
		title = "Security Intern",
		category = "Security",
		description = "Assist with security audits and vulnerability assessments.",
		createdAt = "2024-04-15T13:00:00Z",
		updatedAt = "2024-04-15T13:00:00Z"
	)

	val internship9 = InternshipJoined(
		id = "int-009",
		company = companyB,
		title = "Data Science Intern",
		category = "Data Science",
		description = "Work on data cleaning, modeling, and visualization.",
		createdAt = "2024-04-18T13:00:00Z",
		updatedAt = "2024-04-18T13:00:00Z"
	)

	val internship10 = InternshipJoined(
		id = "int-010",
		company = companyC,
		title = "Cloud Platform Intern",
		category = "Backend",
		description = "Build and maintain cloud infrastructure automation.",
		createdAt = "2024-04-20T13:00:00Z",
		updatedAt = "2024-04-20T13:00:00Z"
	)

	// ------------------------------------------------------------
	// Dummy Students
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

	private val studentUser2 = UserJoined(
		id = "user-002",
		email = "jane.smith@example.com",
		firstName = "Jane",
		lastName = "Smith",
		role = "STUDENT",
		isEmailVerified = true,
		status = "ACTIVE",
		createdAt = "2024-01-20T09:00:00Z",
		updatedAt = "2024-01-20T09:00:00Z"
	)

	private val studentUser3 = UserJoined(
		id = "user-003",
		email = "sam.lee@example.com",
		firstName = "Sam",
		lastName = "Lee",
		role = "STUDENT",
		isEmailVerified = true,
		status = "ACTIVE",
		createdAt = "2024-01-25T09:00:00Z",
		updatedAt = "2024-01-25T09:00:00Z"
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

	private val student2 = StudentJoined(
		user = studentUser2,
		schoolName = "Innovation Institute",
		grade = 12,
		bio = "Loves iOS and design systems.",
		interests = "Swift, SwiftUI, Design",
		avatarUrl = null,
		createdAt = "2024-01-20T09:00:00Z",
		updatedAt = "2024-01-20T09:00:00Z"
	)

	private val student3 = StudentJoined(
		user = studentUser3,
		schoolName = "Data Academy",
		grade = 12,
		bio = "Into data science and DevOps.",
		interests = "Python, ML, CI/CD",
		avatarUrl = null,
		createdAt = "2024-01-25T09:00:00Z",
		updatedAt = "2024-01-25T09:00:00Z"
	)

	// ------------------------------------------------------------
	// Dummy Internship Applications (richer set)
	// ------------------------------------------------------------

	// John
	val application1_applied_int1_john = InternshipApplicationJoined(
		id = "app-001",
		internship = internship1,
		student = student1,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_john_doe.pdf",
		interviewNotes = null,
		createdAt = "2024-04-02T10:00:00Z",
		updatedAt = "2024-04-02T10:00:00Z"
	)

	val application2_rejected_int2_john = InternshipApplicationJoined(
		id = "app-002",
		internship = internship2,
		student = student1,
		status = InternshipApplicationStatus.REJECTED,
		resume = "resume_john_doe.pdf",
		interviewNotes = "Needs stronger Swift fundamentals.",
		createdAt = "2024-04-04T14:00:00Z",
		updatedAt = "2024-04-04T14:00:00Z"
	)

	val application3_accepted_int3_john = InternshipApplicationJoined(
		id = "app-003",
		internship = internship3,
		student = student1,
		status = InternshipApplicationStatus.ACCEPTED,
		resume = "resume_john_doe.pdf",
		interviewNotes = "Great portfolio. Good React knowledge.",
		createdAt = "2024-04-06T12:00:00Z",
		updatedAt = "2024-04-06T12:00:00Z"
	)

	val application4_applied_int4_john = InternshipApplicationJoined(
		id = "app-004",
		internship = internship4,
		student = student1,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_john_doe.pdf",
		interviewNotes = null,
		createdAt = "2024-04-08T09:30:00Z",
		updatedAt = "2024-04-08T09:30:00Z"
	)

	// Jane
	val application5_accepted_int1_jane = InternshipApplicationJoined(
		id = "app-005",
		internship = internship1,
		student = student2,
		status = InternshipApplicationStatus.ACCEPTED,
		resume = "resume_jane_smith.pdf",
		interviewNotes = "Strong Android/Kotlin fundamentals.",
		createdAt = "2024-04-09T09:30:00Z",
		updatedAt = "2024-04-09T09:30:00Z"
	)

	val application6_applied_int2_jane = InternshipApplicationJoined(
		id = "app-006",
		internship = internship2,
		student = student2,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_jane_smith.pdf",
		interviewNotes = null,
		createdAt = "2024-04-09T10:30:00Z",
		updatedAt = "2024-04-09T10:30:00Z"
	)

	val application7_rejected_int6_jane = InternshipApplicationJoined(
		id = "app-007",
		internship = internship6,
		student = student2,
		status = InternshipApplicationStatus.REJECTED,
		resume = "resume_jane_smith.pdf",
		interviewNotes = "Limited CI/CD exposure.",
		createdAt = "2024-04-12T15:30:00Z",
		updatedAt = "2024-04-12T15:30:00Z"
	)

	val application13_applied_int8_jane = InternshipApplicationJoined(
		id = "app-013",
		internship = internship8,
		student = student2,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_jane_smith.pdf",
		interviewNotes = null,
		createdAt = "2024-04-16T09:30:00Z",
		updatedAt = "2024-04-16T09:30:00Z"
	)

	// Sam
	val application8_applied_int6_sam = InternshipApplicationJoined(
		id = "app-008",
		internship = internship6,
		student = student3,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_sam_lee.pdf",
		interviewNotes = null,
		createdAt = "2024-04-12T16:00:00Z",
		updatedAt = "2024-04-12T16:00:00Z"
	)

	val application9_accepted_int7_sam = InternshipApplicationJoined(
		id = "app-009",
		internship = internship7,
		student = student3,
		status = InternshipApplicationStatus.ACCEPTED,
		resume = "resume_sam_lee.pdf",
		interviewNotes = "Great design thinking.",
		createdAt = "2024-04-14T10:00:00Z",
		updatedAt = "2024-04-14T10:00:00Z"
	)

	val application10_applied_int7_john = InternshipApplicationJoined(
		id = "app-010",
		internship = internship7,
		student = student1,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_john_doe.pdf",
		interviewNotes = null,
		createdAt = "2024-04-14T11:00:00Z",
		updatedAt = "2024-04-14T11:00:00Z"
	)

	val application11_rejected_int2_sam = InternshipApplicationJoined(
		id = "app-011",
		internship = internship2,
		student = student3,
		status = InternshipApplicationStatus.REJECTED,
		resume = "resume_sam_lee.pdf",
		interviewNotes = "Portfolio not aligned with iOS role.",
		createdAt = "2024-04-11T10:00:00Z",
		updatedAt = "2024-04-11T10:00:00Z"
	)

	val application12_applied_int1_sam = InternshipApplicationJoined(
		id = "app-012",
		internship = internship1,
		student = student3,
		status = InternshipApplicationStatus.APPLIED,
		resume = "resume_sam_lee.pdf",
		interviewNotes = null,
		createdAt = "2024-04-09T11:00:00Z",
		updatedAt = "2024-04-09T11:00:00Z"
	)

	val application14_accepted_int9_sam = InternshipApplicationJoined(
		id = "app-014",
		internship = internship9,
		student = student3,
		status = InternshipApplicationStatus.ACCEPTED,
		resume = "resume_sam_lee.pdf",
		interviewNotes = "Strong modeling skills.",
		createdAt = "2024-04-19T11:00:00Z",
		updatedAt = "2024-04-19T11:00:00Z"
	)

	val application15_rejected_int10_john = InternshipApplicationJoined(
		id = "app-015",
		internship = internship10,
		student = student1,
		status = InternshipApplicationStatus.REJECTED,
		resume = "resume_john_doe.pdf",
		interviewNotes = "Needs more cloud experience.",
		createdAt = "2024-04-21T11:00:00Z",
		updatedAt = "2024-04-21T11:00:00Z"
	)
}