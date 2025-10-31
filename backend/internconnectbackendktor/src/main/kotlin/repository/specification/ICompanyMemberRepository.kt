package com.internconnect.repository.specification

import com.internconnect.model.companymember.CompanyMember
import java.util.*

interface ICompanyMemberRepository {
	suspend fun findAll(): List<CompanyMember>
	suspend fun findByUserId(userId: UUID): CompanyMember?
	suspend fun create(companyMember: CompanyMember): CompanyMember?
	suspend fun update(companyMember: CompanyMember): CompanyMember?
	suspend fun delete(id: UUID): Boolean
}