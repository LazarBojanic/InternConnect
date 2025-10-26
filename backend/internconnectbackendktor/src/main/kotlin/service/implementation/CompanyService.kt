package com.internconnect.service.implementation

import com.internconnect.model.company.Company
import com.internconnect.repository.implementation.CompanyRepository
import com.internconnect.service.specification.ICompanyService
import java.util.UUID

class CompanyService (
	private val companyRepository: CompanyRepository,
) : ICompanyService {
	override suspend fun getAll(): List<Company> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): Company? {
		TODO("Not yet implemented")
	}

	override suspend fun create(company: Company): Company? {
		TODO("Not yet implemented")
	}

	override suspend fun update(company: Company): Company? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}