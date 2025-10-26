package com.internconnect.repository.specification

import com.internconnect.model.companyinvitation.CompanyInvitation
import java.util.*

interface ICompanyInvitationRepository {
	suspend fun findAll(): List<CompanyInvitation>
	suspend fun findById(id: UUID): CompanyInvitation?
	suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation?
	suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation?
	suspend fun delete(id: UUID): Boolean
}