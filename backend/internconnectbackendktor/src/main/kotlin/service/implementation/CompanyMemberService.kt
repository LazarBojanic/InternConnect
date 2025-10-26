package com.internconnect.service.implementation

import com.internconnect.model.companymember.CompanyMember
import com.internconnect.repository.implementation.CompanyMemberRepository
import com.internconnect.service.specification.ICompanyMemberService
import java.util.UUID

class CompanyMemberService (
	private val companyMemberRepository: CompanyMemberRepository,
) : ICompanyMemberService {
	override suspend fun getAll(): List<CompanyMember> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): CompanyMember? {
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