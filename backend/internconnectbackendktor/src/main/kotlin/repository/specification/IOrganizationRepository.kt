package com.internconnect.repository.specification

import com.internconnect.model.organization.Organization
import java.util.UUID

interface IOrganizationRepository {
	suspend fun findAll(): List<Organization>
	suspend fun findById(id: UUID): Organization?
	suspend fun create(user: Organization): Organization?
	suspend fun update(user: Organization): Organization?
	suspend fun delete(id: UUID): Boolean
}