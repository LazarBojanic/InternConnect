package com.internconnect.model.organization

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*


class OrganizationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<OrganizationEntity>(OrganizationTable)
	var name by OrganizationTable.name
	var industry by OrganizationTable.industry
	var website by OrganizationTable.website
	var logoUrl by OrganizationTable.logoUrl
	var hqCountry by OrganizationTable.hqCountry
	var city by OrganizationTable.city
	var about by OrganizationTable.about
	var createdAt by OrganizationTable.createdAt
	var updatedAt by OrganizationTable.updatedAt
}
