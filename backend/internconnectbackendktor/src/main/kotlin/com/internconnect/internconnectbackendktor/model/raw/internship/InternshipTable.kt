package com.internconnect.internconnectbackendktor.model.raw.internship

import com.internconnect.internconnectbackendktor.model.raw.company.CompanyTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object InternshipTable : UUIDTable(name = "internship") {
	val companyId = reference("company_id", CompanyTable.id)
	val title = varchar("title", length = 255)
	val category = varchar("category", length = 255)
	val description = text("description")
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}