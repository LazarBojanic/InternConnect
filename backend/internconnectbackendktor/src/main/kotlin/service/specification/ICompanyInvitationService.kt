package com.internconnect.service.specification

import com.internconnect.model.companyinvitation.CompanyInvitation
import java.util.*

interface ICompanyInvitationService {
	suspend fun getAll(): List<CompanyInvitation>
	suspend fun getById(id: UUID): CompanyInvitation?
	suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation?
	suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation?
	suspend fun delete(id: UUID): Boolean
}