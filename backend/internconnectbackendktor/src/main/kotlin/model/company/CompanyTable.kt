package com.internconnect.model.company

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object CompanyTable : UUIDTable(name = "company") {
	val name = varchar("name", length = 255)
	val industry = varchar("industry", length = 255)
	val website = varchar("website", length = 255).nullable()
	val logoUrl = varchar("logo_url", length = 255).nullable()
	val hqCountry = varchar("hq_country", length = 255).nullable()
	val city = varchar("city", length = 255).nullable()
	val about = text("about").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}

