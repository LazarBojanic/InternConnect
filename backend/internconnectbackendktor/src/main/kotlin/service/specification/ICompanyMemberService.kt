package com.internconnect.service.specification

import com.internconnect.model.companymember.CompanyMember
import java.util.UUID

interface ICompanyMemberService {
	suspend fun getAll(): List<CompanyMember>
	suspend fun getById(id: UUID): CompanyMember?
	suspend fun create(companyMember: CompanyMember): CompanyMember?
	suspend fun update(companyMember: CompanyMember): CompanyMember?
	suspend fun delete(id: UUID): Boolean
}