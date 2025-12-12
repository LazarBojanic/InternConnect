package com.internconnect.internconnectbackendktor.model.raw.internshipapplication

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.UUID

class InternshipApplicationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<InternshipApplicationEntity>(InternshipApplicationTable)
	var internshipId by InternshipApplicationTable.internshipId
	var studentId by InternshipApplicationTable.studentId
	var status by InternshipApplicationTable.status
	var resume by InternshipApplicationTable.resume
	var interviewNotes by InternshipApplicationTable.interviewNotes
	var createdAt by InternshipApplicationTable.createdAt
	var updatedAt by InternshipApplicationTable.updatedAt
}

