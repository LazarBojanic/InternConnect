package com.internconnect.model.auditlog

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class AuditLogEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<AuditLogEntity>(AuditLogTable)

	var userId by AuditLogTable.userId
	var action by AuditLogTable.action
	var metadata by AuditLogTable.metadata
	var ip by AuditLogTable.ip
	var createdAt by AuditLogTable.createdAt
	var updatedAt by AuditLogTable.updatedAt
}


