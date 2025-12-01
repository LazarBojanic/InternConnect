package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember

interface ICompanyMemberRepository {
	suspend fun getCurrentCompanyMember(): CompanyMember?
	suspend fun setCurrentCompanyMember(companyMember: CompanyMember)
	suspend fun clear()
}