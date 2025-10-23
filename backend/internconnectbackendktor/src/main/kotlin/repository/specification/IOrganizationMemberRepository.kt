package com.internconnect.repository.specification

import com.internconnect.model.organization.Organization
import com.internconnect.model.organizationmember.OrganizationMember
import com.internconnect.model.user.User
import java.util.UUID

interface IOrganizationMemberRepository {
	suspend fun findAll(): List<OrganizationMember>
	suspend fun findById(id: UUID): OrganizationMember?
	suspend fun create(user: OrganizationMember): OrganizationMember?
	suspend fun update(user: OrganizationMember): OrganizationMember?
	suspend fun delete(id: UUID): Boolean
}