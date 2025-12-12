package com.internconnect.internconnectbackendktor.model.raw.internshipapplication

import com.internconnect.internconnectbackendktor.model.raw.internship.InternshipTable
import com.internconnect.internconnectbackendktor.model.raw.student.StudentTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object InternshipApplicationTable : UUIDTable(name = "internship_application") {
	val internshipId = reference("internship_id", InternshipTable.id)
	val studentId = reference("student_id", StudentTable.userId)
	val resume = blob("resume")
	val status = enumeration("status", InternshipApplicationStatus::class)
	val interviewNotes = text("interview_notes").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}