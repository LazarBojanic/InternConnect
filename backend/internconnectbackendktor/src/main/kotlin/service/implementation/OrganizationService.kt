package com.internconnect.service.implementation

import com.internconnect.model.organization.Organization
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.OrganizationRepository
import com.internconnect.repository.specification.IOrganizationRepository
import com.internconnect.service.specification.IOrganizationService
import java.util.UUID

class OrganizationService (
	private val organizationRepository: OrganizationRepository,
) : IOrganizationService {
	override suspend fun getAll(): List<Organization> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): Organization? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: Organization): Organization? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: Organization): Organization? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}