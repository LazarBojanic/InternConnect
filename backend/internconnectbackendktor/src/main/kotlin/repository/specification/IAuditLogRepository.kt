package com.internconnect.repository.specification

import com.internconnect.model.auditlog.AuditLog
import java.util.*

interface IAuditLogRepository {
	suspend fun findAll(): List<AuditLog>
	suspend fun findById(id: UUID): AuditLog?
	suspend fun create(auditLog: AuditLog): AuditLog?
	suspend fun update(auditLog: AuditLog): AuditLog?
	suspend fun delete(id: UUID): Boolean
}