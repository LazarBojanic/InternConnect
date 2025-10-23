package com.internconnect.service.implementation

import com.internconnect.model.organizationmember.OrganizationMember
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.OrganizationMemberRepository
import com.internconnect.repository.specification.IOrganizationMemberRepository
import com.internconnect.service.specification.IOrganizationMemberService
import java.util.UUID

class OrganizationMemberService (
	private val organizationMemberRepository: OrganizationMemberRepository,
) : IOrganizationMemberService {
	override suspend fun getAll(): List<OrganizationMember> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): OrganizationMember? {
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