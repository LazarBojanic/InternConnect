package com.internconnect.repository.implementation

import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import com.internconnect.model.internshipapplication.InternshipApplication
import com.internconnect.model.internshipapplication.InternshipApplicationStatus
import com.internconnect.repository.specification.IInternshipApplicationRepository
import java.util.UUID

class InternshipApplicationRepository : IInternshipApplicationRepository {
	override suspend fun findAll(): List<InternshipApplication> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): InternshipApplication? {
		TODO("Not yet implemented")
	}

	override suspend fun findByCompanyId(companyId: UUID): List<InternshipApplication> {
		TODO("Not yet implemented")
	}

	override suspend fun findByStudentId(studentId: UUID): List<InternshipApplication> {
		TODO("Not yet implemented")
	}

	override suspend fun findByApplicationStatus(applicationStatus: InternshipApplicationStatus): List<InternshipApplication> {
		TODO("Not yet implemented")
	}

	override suspend fun create(internshipApplication: InternshipApplication): InternshipApplication? {
		TODO("Not yet implemented")
	}

	override suspend fun update(internshipApplication: InternshipApplication): InternshipApplication? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}