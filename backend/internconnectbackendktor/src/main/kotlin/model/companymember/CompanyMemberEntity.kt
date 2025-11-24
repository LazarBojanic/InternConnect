package com.internconnect.model.companymember

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class CompanyMemberEntity(userId: EntityID<UUID>) : UUIDEntity(userId) {
	companion object : UUIDEntityClass<CompanyMemberEntity>(CompanyMemberTable)

	var companyID by CompanyMemberTable.companyID
	var userId by CompanyMemberTable.userId
	var companyMemberRole by CompanyMemberTable.companyMemberRole
	var companyMemberStatus by CompanyMemberTable.companyMemberStatus
	var joinedAt by CompanyMemberTable.joinedAt
	var createdAt by CompanyMemberTable.createdAt
	var updatedAt by CompanyMemberTable.updatedAt
}
