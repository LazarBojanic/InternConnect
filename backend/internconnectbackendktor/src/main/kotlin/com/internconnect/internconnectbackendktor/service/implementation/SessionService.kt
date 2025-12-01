package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.SessionJoined
import com.internconnect.internconnectbackendktor.model.raw.session.Session
import com.internconnect.internconnectbackendktor.repository.specification.ISessionRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.ISessionService
import java.util.UUID

class SessionService(
	private val userRepository: IUserRepository,
	private val sessionRepository: ISessionRepository,
) : ISessionService {

	override suspend fun findAll(): List<SessionJoined> {
		val sessions = sessionRepository.findAll()
		val sessionsJoined = mutableListOf<SessionJoined>()
		for(session: Session in sessions){
			val user = userRepository.findById(session.userId)
			if(user != null){
				sessionsJoined.add(session.join(user.join()))
			}
		}
		return sessionsJoined
	}
	override suspend fun findById(id: UUID): SessionJoined? {
		val session = sessionRepository.findById(id)
		if(session != null){
			val user = userRepository.findById(session.userId)
			if(user != null){
				return session.join(user.join())
			}
		}
		return null
	}

	override suspend fun findByUserId(userId: UUID): SessionJoined? {
		val session = sessionRepository.findByUserId(userId)
		if(session != null){
			val user = userRepository.findById(session.userId)
			if(user != null){
				return session.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(session: Session): SessionJoined? {
		val created = sessionRepository.create(session)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(session: Session): SessionJoined? {
		val updated = sessionRepository.update(session)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return sessionRepository.delete(id)
	}
}