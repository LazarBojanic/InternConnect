package com.internconnect.model.companymember

import com.internconnect.model.company.CompanyTable
import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant
import java.util.*

object CompanyMemberTable : IdTable<UUID>(name = "company_member") {
	override val id: Column<EntityID<UUID>> =
		reference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE).uniqueIndex()
	val userId = id
	val companyID = reference("company_id", CompanyTable.id)
	val companyMemberRole = varchar("company_member_role", length = 255)
	val companyMemberStatus = varchar("company_member_status", length = 255)
	val joinedAt = timestamp("joined_at").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
