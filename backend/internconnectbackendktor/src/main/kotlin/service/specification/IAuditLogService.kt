package com.internconnect.service.specification

import com.internconnect.model.auditlog.AuditLog
import java.util.UUID

interface IAuditLogService {
	suspend fun getAll(): List<AuditLog>
	suspend fun getById(id: UUID): AuditLog?
	suspend fun create(user: AuditLog): AuditLog?
	suspend fun update(user: AuditLog): AuditLog?
	suspend fun delete(id: UUID): Boolean
}