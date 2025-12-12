package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.CompanyJoined
import com.internconnect.internconnectbackendktor.model.raw.company.Company
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyRepository
import com.internconnect.internconnectbackendktor.service.specification.ICompanyService
import java.util.*

class CompanyService(
	private val companyRepository: ICompanyRepository,
) : ICompanyService {
	override suspend fun getAll(): List<CompanyJoined> {
		val companies = companyRepository.findAll()
		val companiesJoined = mutableListOf<CompanyJoined>()
		for(company: Company in companies) {
			companiesJoined.add(company.join())
		}
		return companiesJoined
	}

	override suspend fun getById(id: UUID): CompanyJoined? {
		val company = companyRepository.findById(id)
		if(company != null){
			return company.join()
		}
		return null
	}

	override suspend fun getByName(name: String): CompanyJoined? {
		val company = companyRepository.findByName(name)
		if(company != null){
			return company.join()
		}
		return null
	}

	override suspend fun create(company: Company): CompanyJoined? {
		val created = companyRepository.create(company)
		if(created != null){
			return created.join()
		}
		return null
	}

	override suspend fun update(company: Company): CompanyJoined? {
		val updated = companyRepository.update(company)
		if(updated != null){
			return updated.join()
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return companyRepository.delete(id)
	}
}