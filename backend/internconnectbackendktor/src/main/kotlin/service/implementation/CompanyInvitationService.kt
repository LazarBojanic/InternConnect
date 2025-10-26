package com.internconnect.service.implementation

import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.repository.implementation.CompanyInvitationRepository
import com.internconnect.service.specification.ICompanyInvitationService
import java.util.UUID

class CompanyInvitationService (
	private val companyInvitationRepository: CompanyInvitationRepository,
) : ICompanyInvitationService {
	override suspend fun getAll(): List<CompanyInvitation> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): CompanyInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}