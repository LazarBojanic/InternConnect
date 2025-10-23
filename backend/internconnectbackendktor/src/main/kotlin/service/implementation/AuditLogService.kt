package com.internconnect.service.implementation

import com.internconnect.model.auditlog.AuditLog
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.specification.IAuditLogRepository
import com.internconnect.service.specification.IAuditLogService
import java.util.UUID

class AuditLogService (
	private val auditLogRepository: AuditLogRepository,
): IAuditLogService {
	override suspend fun getAll(): List<AuditLog> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): AuditLog? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: AuditLog): AuditLog? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: AuditLog): AuditLog? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}