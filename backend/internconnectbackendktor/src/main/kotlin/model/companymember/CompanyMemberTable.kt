package com.internconnect.model.companymember

import com.internconnect.model.company.CompanyTable
import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object CompanyMemberTable : UUIDTable(name = "company_member") {
	val companyID = reference("company_id", CompanyTable.id)
	val userId = reference("user_id", UserTable.id)
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
