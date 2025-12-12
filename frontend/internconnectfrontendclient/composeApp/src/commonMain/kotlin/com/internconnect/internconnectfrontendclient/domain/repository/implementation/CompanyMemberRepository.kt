package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.CompanyMemberDao
import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyMemberRepository
import kotlinx.coroutines.flow.first

class CompanyMemberRepository(
	private val companyMemberDao: CompanyMemberDao
) : ICompanyMemberRepository {
	override suspend fun getCurrentCompanyMember(): CompanyMember? {
		return companyMemberDao.findFirst().first()
	}

	override suspend fun setCurrentCompanyMember(companyMember: CompanyMember) {
		companyMemberDao.upsert(companyMember)
	}

	override suspend fun clear() {
		companyMemberDao.clearAll()
	}
}