package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplication
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationStatus
import java.util.UUID

interface IInternshipApplicationService {
	suspend fun getAll(): List<InternshipApplicationJoined>
	suspend fun getById(id: UUID): InternshipApplicationJoined?
	suspend fun getByInternshipId(internshipId: UUID): List<InternshipApplicationJoined>
	suspend fun getByStudentId(studentId: UUID): List<InternshipApplicationJoined>
	suspend fun getByStatus(status: InternshipApplicationStatus): List<InternshipApplicationJoined>
	suspend fun create(internshipApplication: InternshipApplication): InternshipApplicationJoined?
	suspend fun update(internshipApplication: InternshipApplication): InternshipApplicationJoined?
	suspend fun delete(id: UUID): Boolean
}