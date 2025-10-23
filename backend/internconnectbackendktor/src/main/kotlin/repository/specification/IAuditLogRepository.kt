package com.internconnect.repository.specification

import com.internconnect.model.auditlog.AuditLog
import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.model.user.User
import java.util.UUID

interface IAuditLogRepository {
	suspend fun findAll(): List<AuditLog>
	suspend fun findById(id: UUID): AuditLog?
	suspend fun create(user: AuditLog): AuditLog?
	suspend fun update(user: AuditLog): AuditLog?
	suspend fun delete(id: UUID): Boolean
}