package com.internconnect.repository.implementation

import com.internconnect.model.company.Company
import com.internconnect.repository.specification.ICompanyRepository
import java.util.*

class CompanyRepository : ICompanyRepository {
	override suspend fun findAll(): List<Company> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): Company? {
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