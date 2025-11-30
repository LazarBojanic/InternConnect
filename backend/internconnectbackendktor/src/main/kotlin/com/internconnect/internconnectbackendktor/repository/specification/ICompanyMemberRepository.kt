package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMember
import java.util.*

interface ICompanyMemberRepository {
	suspend fun findAll(): List<CompanyMember>
	suspend fun findById(id: UUID): CompanyMember?
	suspend fun create(companyMember: CompanyMember): CompanyMember?
	suspend fun update(companyMember: CompanyMember): CompanyMember?
	suspend fun delete(id: UUID): Boolean
}