package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.auditlog.AuditLog
import com.internconnect.model.auditlog.AuditLogEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IAuditLogRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class AuditLogRepository : IAuditLogRepository {
	override suspend fun findAll(): List<AuditLog> {
		return transaction {
			AuditLogEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): AuditLog? {
		return transaction {
			AuditLogEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun create(auditLog: AuditLog): AuditLog? {
		return transaction {
			AuditLogEntity.new(auditLog.id) {
				setFrom(auditLog, MapMode.Insert)
			}.toDomain()
		}
	}

	override suspend fun update(auditLog: AuditLog): AuditLog? {
		return transaction {
			val e = AuditLogEntity.findById(auditLog.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(auditLog.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = AuditLogEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}