package com.internconnect.service.implementation

import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import com.internconnect.repository.specification.IInternshipRepository
import com.internconnect.service.specification.IInternshipService
import java.util.UUID

class InternshipService(
	private val internshipRepository: IInternshipRepository,
) : IInternshipService {
	override suspend fun getAll(): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun getByCompanyId(companyId: UUID): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun getByCategory(category: String): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun create(company: Company): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun update(company: Company): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}