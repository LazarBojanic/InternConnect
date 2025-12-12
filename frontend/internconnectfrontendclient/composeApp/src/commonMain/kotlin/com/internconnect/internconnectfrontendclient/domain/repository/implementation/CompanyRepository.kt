package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.CompanyDao
import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyRepository
import kotlinx.coroutines.flow.first

class CompanyRepository(
	private val companyDao: CompanyDao
) : ICompanyRepository {
	override suspend fun getCurrentCompany(): Company? {
		return companyDao.findFirst().first()
	}

	override suspend fun setCurrentCompany(company: Company) {
		return companyDao.upsert(company)
	}

	override suspend fun clear() {
		companyDao.clearAll()
	}
}