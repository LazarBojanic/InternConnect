package com.internconnect.service.implementation

import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import com.internconnect.model.internshipapplication.InternshipApplication
import com.internconnect.model.internshipapplication.InternshipApplicationStatus
import com.internconnect.repository.specification.IInternshipApplicationRepository
import com.internconnect.service.specification.IInternshipApplicationService
import java.util.UUID

class InternshipApplicationService(
	private val internshipApplicationRepository: IInternshipApplicationRepository,
) : IInternshipApplicationService{
	override suspend fun getAll(): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun getByCompanyId(companyId: UUID): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun getByStudentId(studentId: UUID): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun getByApplicationStatus(applicationStatus: InternshipApplicationStatus): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun create(internshipApplication: InternshipApplication): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun update(internshipApplication: InternshipApplication): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}