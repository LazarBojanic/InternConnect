package com.internconnect.internconnectbackendktor.model.raw.internship

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class InternshipEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<InternshipEntity>(InternshipTable)
	var companyId by InternshipTable.companyId
	var title by InternshipTable.title
	var category by InternshipTable.category
	var description by InternshipTable.description
	var createdAt by InternshipTable.createdAt
	var updatedAt by InternshipTable.updatedAt
}