package com.internconnect.service.specification

import com.internconnect.model.internship.Internship
import com.internconnect.model.internshipapplication.InternshipApplication
import com.internconnect.model.internshipapplication.InternshipApplicationStatus
import java.util.UUID

interface IInternshipApplicationService {
	suspend fun getAll(): List<Internship>
	suspend fun getById(id: UUID): Internship?
	suspend fun getByCompanyId(companyId: UUID): List<Internship>
	suspend fun getByStudentId(studentId: UUID): List<Internship>
	suspend fun getByApplicationStatus(applicationStatus: InternshipApplicationStatus): List<Internship>
	suspend fun create(internshipApplication: InternshipApplication): Internship?
	suspend fun update(internshipApplication: InternshipApplication): Internship?
	suspend fun delete(id: UUID): Boolean
}