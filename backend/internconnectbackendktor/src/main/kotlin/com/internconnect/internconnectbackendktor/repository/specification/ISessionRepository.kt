package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.session.Session
import java.util.UUID

interface ISessionRepository {
	suspend fun findAll(): List<Session>
	suspend fun findById(id: UUID): Session?
	suspend fun findByUserId(userId: UUID): Session?
	suspend fun create(session: Session): Session?
	suspend fun update(session: Session): Session?
	suspend fun delete(id: UUID): Boolean
}