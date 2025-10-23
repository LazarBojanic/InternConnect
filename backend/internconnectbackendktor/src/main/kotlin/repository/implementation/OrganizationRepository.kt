package com.internconnect.repository.implementation

import com.internconnect.model.organization.Organization
import com.internconnect.repository.specification.IOrganizationRepository
import java.util.UUID

class OrganizationRepository : IOrganizationRepository {
	override suspend fun findAll(): List<Organization> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): Organization? {
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