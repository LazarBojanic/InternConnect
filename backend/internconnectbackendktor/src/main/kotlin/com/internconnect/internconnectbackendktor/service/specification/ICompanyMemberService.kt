package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.CompanyMemberJoined
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMember
import java.util.*

interface ICompanyMemberService {
	suspend fun getAll(): List<CompanyMemberJoined>
	suspend fun getById(id: UUID): CompanyMemberJoined?
	suspend fun create(companyMember: CompanyMember): CompanyMemberJoined?
	suspend fun update(companyMember: CompanyMember): CompanyMemberJoined?
	suspend fun delete(id: UUID): Boolean
}