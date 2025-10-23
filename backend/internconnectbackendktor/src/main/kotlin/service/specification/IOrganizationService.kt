package com.internconnect.service.specification

import com.internconnect.model.organization.Organization
import java.util.UUID

interface IOrganizationService {
	suspend fun getAll(): List<Organization>
	suspend fun getById(id: UUID): Organization?
	suspend fun create(user: Organization): Organization?
	suspend fun update(user: Organization): Organization?
	suspend fun delete(id: UUID): Boolean
}