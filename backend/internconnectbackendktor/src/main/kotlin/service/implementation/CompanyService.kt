package com.internconnect.service.implementation

import com.internconnect.model.company.Company
import com.internconnect.repository.specification.ICompanyRepository
import com.internconnect.service.specification.ICompanyService
import java.util.*

class CompanyService (
	private val companyRepository: ICompanyRepository,
) : ICompanyService {
	override suspend fun getAll(): List<Company> {
		return companyRepository.findAll()
	}

	override suspend fun getById(id: UUID): Company? {
		return companyRepository.findById(id)
	}

	override suspend fun create(company: Company): Company? {
		return companyRepository.create(company)
	}

	override suspend fun update(company: Company): Company? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		return companyRepository.delete(id)
	}
}