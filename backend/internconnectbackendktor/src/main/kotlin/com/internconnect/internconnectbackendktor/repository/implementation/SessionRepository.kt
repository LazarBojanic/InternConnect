package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.session.Session
import com.internconnect.internconnectbackendktor.model.raw.session.SessionEntity
import com.internconnect.internconnectbackendktor.model.raw.session.SessionTable
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.ISessionRepository
import kotlinx.html.Entities
import org.jetbrains.exposed.v1.core.eq
import java.util.UUID

class SessionRepository : ISessionRepository {
	override suspend fun findAll(): List<Session> {
		return dbQuery{
			SessionEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): Session? {
		return dbQuery { SessionEntity.findById(id)?.toDomain() }
	}

	override suspend fun findByUserId(userId: UUID): Session? {
		return dbQuery {
			SessionEntity.find { SessionTable.userId eq userId }.firstOrNull()?.toDomain()
		}
	}

	override suspend fun create(session: Session): Session? {
		return dbQuery { SessionEntity.new(session.id) { setFrom(session, MapMode.Insert) }.toDomain() }
	}

	override suspend fun update(session: Session): Session? {
		return dbQuery {
			val e = SessionEntity.findById(session.id) ?: return@dbQuery null
			e.setFrom(session, MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = SessionEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}