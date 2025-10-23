package com.internconnect.service.specification

import com.internconnect.model.organizationinvitation.OrganizationInvitation
import java.util.UUID

interface IOrganizationInvitationService {
	suspend fun getAll(): List<OrganizationInvitation>
	suspend fun getById(id: UUID): OrganizationInvitation?
	suspend fun create(user: OrganizationInvitation): OrganizationInvitation?
	suspend fun update(user: OrganizationInvitation): OrganizationInvitation?
	suspend fun delete(id: UUID): Boolean
}