package com.internconnect.repository.implementation

import com.internconnect.model.auditlog.AuditLog
import com.internconnect.model.auditlog.AuditLogEntity
import com.internconnect.repository.specification.IAuditLogRepository
import java.net.InetAddress
import java.util.*

class AuditLogRepository : IAuditLogRepository {
	override suspend fun findAll(): List<AuditLog> {
		var auditLogList: List<AuditLog> = emptyList()
		val auditLogEntityList = AuditLogEntity.all()
		auditLogEntityList.forEach { auditLogEntity ->
			val auditLog = AuditLog(
				id = auditLogEntity.id.value,
				userId = auditLogEntity.userId.value,
				action = auditLogEntity.action,
				metadata = auditLogEntity.metadata,
				ip = InetAddress.getByName(auditLogEntity.ip),
				createdAt = auditLogEntity.createdAt,
				updatedAt = auditLogEntity.updatedAt
			)
		}
		return AuditLogEntity.all().toList()
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