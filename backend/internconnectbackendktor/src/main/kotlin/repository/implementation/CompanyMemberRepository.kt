package com.internconnect.repository.implementation

import com.internconnect.model.companymember.CompanyMember
import com.internconnect.repository.specification.ICompanyMemberRepository
import java.util.UUID

class CompanyMemberRepository : ICompanyMemberRepository {
	override suspend fun findAll(): List<CompanyMember> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): CompanyMember? {
		TODO("Not yet implemented")
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMember? {
		TODO("Not yet implemented")
	}

	override suspend fun update(companyMember: CompanyMember): CompanyMember? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}