package com.internconnect.repository.implementation

import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.repository.specification.ICompanyInvitationRepository
import java.util.UUID

class CompanyInvitationRepository : ICompanyInvitationRepository {
	override suspend fun findAll(): List<CompanyInvitation> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): CompanyInvitation? {
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