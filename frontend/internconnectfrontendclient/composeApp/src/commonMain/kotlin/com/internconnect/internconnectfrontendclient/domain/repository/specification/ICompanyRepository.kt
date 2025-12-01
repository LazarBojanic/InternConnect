package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.Company

interface ICompanyRepository {
	suspend fun getCurrentCompany(): Company?
	suspend fun setCurrentCompany(company: Company)
	suspend fun clear()
}