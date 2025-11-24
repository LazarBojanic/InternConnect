package com.internconnect.model.company

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*


class CompanyEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<CompanyEntity>(CompanyTable)

	var name by CompanyTable.name
	var industry by CompanyTable.industry
	var website by CompanyTable.website
	var logoUrl by CompanyTable.logoUrl
	var hqCountry by CompanyTable.hqCountry
	var city by CompanyTable.city
	var about by CompanyTable.about
	var createdAt by CompanyTable.createdAt
	var updatedAt by CompanyTable.updatedAt
}
