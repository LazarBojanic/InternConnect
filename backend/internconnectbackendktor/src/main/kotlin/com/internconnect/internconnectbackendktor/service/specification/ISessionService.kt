package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.SessionJoined
import com.internconnect.internconnectbackendktor.model.raw.session.Session
import java.util.UUID

interface ISessionService {
	suspend fun findAll(): List<SessionJoined>
	suspend fun findById(id: UUID): SessionJoined?
	suspend fun findByUserId(userId: UUID): SessionJoined?
	suspend fun create(session: Session): SessionJoined?
	suspend fun update(session: Session): SessionJoined?
	suspend fun delete(id: UUID): Boolean
}