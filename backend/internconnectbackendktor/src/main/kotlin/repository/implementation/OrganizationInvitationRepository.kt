package com.internconnect.repository.implementation

import com.internconnect.model.organizationinvitation.OrganizationInvitation
import com.internconnect.repository.specification.IOrganizationInvitationRepository
import java.util.UUID

class OrganizationInvitationRepository : IOrganizationInvitationRepository {
	override suspend fun findAll(): List<OrganizationInvitation> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): OrganizationInvitation? {
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