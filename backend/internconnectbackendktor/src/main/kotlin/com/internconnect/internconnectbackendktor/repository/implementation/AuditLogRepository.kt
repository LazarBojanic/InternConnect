package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.auditlog.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IAuditLogRepository
import java.time.Instant
import java.util.*

class AuditLogRepository : IAuditLogRepository {
	override suspend fun findAll(): List<AuditLog> {
		return dbQuery {
			AuditLogEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): AuditLog? {
		return dbQuery {
			AuditLogEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun create(auditLog: AuditLog): AuditLog? {
		return dbQuery {
			AuditLogEntity.new(auditLog.id) {
				setFrom(auditLog, MapMode.Insert)
			}.toDomain()
		}
	}

	override suspend fun update(auditLog: AuditLog): AuditLog? {
		return dbQuery {
			val e = AuditLogEntity.findById(auditLog.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(auditLog.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = AuditLogEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}