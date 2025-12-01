package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.AuditLogJoined
import com.internconnect.internconnectbackendktor.model.raw.auditlog.*
import java.util.*

interface IAuditLogService {
	suspend fun getAll(): List<AuditLogJoined>
	suspend fun getById(id: UUID): AuditLogJoined?
	suspend fun create(auditLog: AuditLog): AuditLogJoined?
	suspend fun update(auditLog: AuditLog): AuditLogJoined?
	suspend fun delete(id: UUID): Boolean
}