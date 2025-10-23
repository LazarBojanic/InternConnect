package com.internconnect.service.specification

import com.internconnect.model.organizationmember.OrganizationMember
import java.util.UUID

interface IOrganizationMemberService {
	suspend fun getAll(): List<OrganizationMember>
	suspend fun getById(id: UUID): OrganizationMember?
	suspend fun create(user: OrganizationMember): OrganizationMember?
	suspend fun update(user: OrganizationMember): OrganizationMember?
	suspend fun delete(id: UUID): Boolean
}