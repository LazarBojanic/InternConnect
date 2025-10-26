package com.internconnect.model.companymember

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class CompanyMemberEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<CompanyMemberEntity>(CompanyMemberTable)
	var companyID by CompanyMemberTable.companyID
	var userId by CompanyMemberTable.userId
	var createdAt by CompanyMemberTable.createdAt
	var updatedAt by CompanyMemberTable.updatedAt
}

