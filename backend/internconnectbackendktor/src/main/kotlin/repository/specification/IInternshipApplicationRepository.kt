package com.internconnect.repository.specification

import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import com.internconnect.model.internshipapplication.InternshipApplication
import com.internconnect.model.internshipapplication.InternshipApplicationStatus
import java.util.UUID

interface IInternshipApplicationRepository {
	suspend fun findAll(): List<InternshipApplication>
	suspend fun findById(id: UUID): InternshipApplication?
	suspend fun findByCompanyId(companyId: UUID): List<InternshipApplication>
	suspend fun findByStudentId(studentId: UUID): List<InternshipApplication>
	suspend fun findByApplicationStatus(applicationStatus: InternshipApplicationStatus): List<InternshipApplication>
	suspend fun create(internshipApplication: InternshipApplication): InternshipApplication?
	suspend fun update(internshipApplication: InternshipApplication): InternshipApplication?
	suspend fun delete(id: UUID): Boolean
}