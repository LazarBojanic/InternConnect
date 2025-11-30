package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplication
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationStatus
import java.util.UUID

interface IInternshipApplicationRepository {
	suspend fun findAll(): List<InternshipApplication>
	suspend fun findById(id: UUID): InternshipApplication?
	suspend fun findByInternshipId(internshipId: UUID): List<InternshipApplication>
	suspend fun findByStudentId(studentId: UUID): List<InternshipApplication>
	suspend fun findByStatus(status: InternshipApplicationStatus): List<InternshipApplication>
	suspend fun create(internshipApplication: InternshipApplication): InternshipApplication?
	suspend fun update(internshipApplication: InternshipApplication): InternshipApplication?
	suspend fun delete(id: UUID): Boolean
}