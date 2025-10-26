package com.internconnect.service.implementation

import com.internconnect.model.auditlog.AuditLog
import com.internconnect.repository.specification.IAuditLogRepository
import com.internconnect.service.specification.IAuditLogService
import java.util.*

class AuditLogService (
	private val auditLogRepository: IAuditLogRepository,
): IAuditLogService {
	override suspend fun getAll(): List<AuditLog> {
		return auditLogRepository.findAll()
	}

	override suspend fun getById(id: UUID): AuditLog? {
		return auditLogRepository.findById(id)
	}

	override suspend fun create(user: AuditLog): AuditLog? {
		return auditLogRepository.create(user)
	}

	override suspend fun update(user: AuditLog): AuditLog? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		return auditLogRepository.delete(id)
	}
}