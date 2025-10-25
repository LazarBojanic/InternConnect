package com.internconnect.model.organizationmember

import com.internconnect.model.organization.OrganizationTable
import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object OrganizationMemberTable : UUIDTable(name = "organization_member") {
	val organizationID = reference("organization_id", OrganizationTable.id)
	val userID = reference("user_id", UserTable.id)
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
