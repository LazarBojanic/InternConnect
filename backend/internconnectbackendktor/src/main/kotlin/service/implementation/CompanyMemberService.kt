package com.internconnect.service.implementation

import com.internconnect.model.companymember.CompanyMember
import com.internconnect.repository.specification.ICompanyMemberRepository
import com.internconnect.service.specification.ICompanyMemberService
import java.util.*

class CompanyMemberService(
	private val companyMemberRepository: ICompanyMemberRepository,
) : ICompanyMemberService {
	override suspend fun getAll(): List<CompanyMember> {
		return companyMemberRepository.findAll()
	}

	override suspend fun getByUserId(userId: UUID): CompanyMember? {
		return companyMemberRepository.findByUserId(userId)
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMember? {
		return companyMemberRepository.create(companyMember)
	}

	override suspend fun update(companyMember: CompanyMember): CompanyMember? {
		return companyMemberRepository.update(companyMember)
	}

	override suspend fun delete(id: UUID): Boolean {
		return companyMemberRepository.delete(id)
	}
}