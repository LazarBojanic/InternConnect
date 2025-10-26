package com.internconnect.service.specification

import com.internconnect.model.auditlog.AuditLog
import java.util.*

interface IAuditLogService {
	suspend fun getAll(): List<AuditLog>
	suspend fun getById(id: UUID): AuditLog?
	suspend fun create(auditLog: AuditLog): AuditLog?
	suspend fun update(auditLog: AuditLog): AuditLog?
	suspend fun delete(id: UUID): Boolean
}