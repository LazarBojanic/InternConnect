package com.internconnect.repository.specification

import com.internconnect.model.organizationinvitation.OrganizationInvitation
import com.internconnect.model.organizationmember.OrganizationMember
import com.internconnect.model.user.User
import java.util.UUID

interface IOrganizationInvitationRepository {
	suspend fun findAll(): List<OrganizationInvitation>
	suspend fun findById(id: UUID): OrganizationInvitation?
	suspend fun create(user: OrganizationInvitation): OrganizationInvitation?
	suspend fun update(user: OrganizationInvitation): OrganizationInvitation?
	suspend fun delete(id: UUID): Boolean
}