package com.internconnect.repository.implementation

import com.internconnect.model.organizationmember.OrganizationMember
import com.internconnect.repository.specification.IOrganizationMemberRepository
import java.util.UUID

class OrganizationMemberRepository : IOrganizationMemberRepository {
	override suspend fun findAll(): List<OrganizationMember> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): OrganizationMember? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: OrganizationMember): OrganizationMember? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: OrganizationMember): OrganizationMember? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}