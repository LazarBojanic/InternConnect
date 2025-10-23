package com.internconnect.model.organizationmember

import com.internconnect.model.organization.OrganizationTable
import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object OrganizationMemberTable : UUIDTable(name = "organization_member") {
	val organizationID = reference("organization_id", OrganizationTable.id)
	val userID = reference("user_id", UserTable.id)
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
