package com.internconnect.model.organization

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object OrganizationTable : UUIDTable(name = "organization") {
	val name = varchar("name", length = 255)
	val industry = varchar("industry", length = 255)
	val website = varchar("website", length = 255).nullable()
	val logoUrl = varchar("logo_url", length = 255).nullable()
	val hqCountry = varchar("hq_country", length = 255).nullable()
	val city = varchar("city", length = 255).nullable()
	val about = varchar("about", length = 255).nullable()
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}

