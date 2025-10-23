package com.internconnect.model.organizationmember

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class OrganizationMemberEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<OrganizationMemberEntity>(OrganizationMemberTable)
	var organizationID by OrganizationMemberTable.organizationID
	var userID by OrganizationMemberTable.userID
	var createdAt by OrganizationMemberTable.createdAt
	var updatedAt by OrganizationMemberTable.updatedAt
}

