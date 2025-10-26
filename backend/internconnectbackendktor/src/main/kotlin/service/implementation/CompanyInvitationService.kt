package com.internconnect.service.implementation

import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.repository.specification.ICompanyInvitationRepository
import com.internconnect.service.specification.ICompanyInvitationService
import java.util.*

class CompanyInvitationService (
	private val companyInvitationRepository: ICompanyInvitationRepository,
) : ICompanyInvitationService {
	override suspend fun getAll(): List<CompanyInvitation> {
		return companyInvitationRepository.findAll()
	}

	override suspend fun getById(id: UUID): CompanyInvitation? {
		return companyInvitationRepository.findById(id)
	}

	override suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return companyInvitationRepository.create(companyInvitation)
	}

	override suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return companyInvitationRepository.update(companyInvitation)
	}

	override suspend fun delete(id: UUID): Boolean {
		return companyInvitationRepository.delete(id)
	}
}