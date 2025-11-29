package com.internconnect.model.internshipapplication

import com.internconnect.model.internship.InternshipTable
import com.internconnect.model.student.StudentTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object InternshipApplicationTable : UUIDTable(name = "internship_application") {
	val internshipId = reference("internship_id", InternshipTable.id)
	val studentId = reference("title", StudentTable.userId)
	val resume = blob("resume")
	val status = varchar("status", length = 255)
	val interviewNotes = text("interview_notes").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}