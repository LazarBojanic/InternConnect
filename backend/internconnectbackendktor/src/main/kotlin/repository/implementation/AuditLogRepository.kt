package com.internconnect.repository.implementation

import com.internconnect.model.auditlog.AuditLog
import com.internconnect.repository.specification.IAuditLogRepository
import java.util.*

class AuditLogRepository : IAuditLogRepository {
	override suspend fun findAll(): List<AuditLog> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): AuditLog? {
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