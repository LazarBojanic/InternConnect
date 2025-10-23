package com.internconnect.service.implementation

import com.internconnect.model.organizationinvitation.OrganizationInvitation
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.OrganizationInvitationRepository
import com.internconnect.repository.specification.IOrganizationInvitationRepository
import com.internconnect.service.specification.IOrganizationInvitationService
import java.util.UUID

class OrganizationInvitationService (
	private val organizationInvitationRepository: OrganizationInvitationRepository,
) : IOrganizationInvitationService {
	override suspend fun getAll(): List<OrganizationInvitation> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): OrganizationInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: OrganizationInvitation): OrganizationInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: OrganizationInvitation): OrganizationInvitation? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}