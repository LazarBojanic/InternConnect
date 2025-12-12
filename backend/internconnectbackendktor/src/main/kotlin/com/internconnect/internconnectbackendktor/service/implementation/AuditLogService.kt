package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.AuditLogJoined
import com.internconnect.internconnectbackendktor.model.raw.auditlog.AuditLog
import com.internconnect.internconnectbackendktor.repository.specification.IAuditLogRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IAuditLogService
import java.util.*

class AuditLogService(
	private val userRepository: IUserRepository,
	private val auditLogRepository: IAuditLogRepository,
) : IAuditLogService {
	override suspend fun getAll(): List<AuditLogJoined> {
		val auditLogs = auditLogRepository.findAll()
		val auditLogsJoined = mutableListOf<AuditLogJoined>()
		for(auditLog: AuditLog in auditLogs) {
			val user = userRepository.findById(auditLog.userId)
			if(user != null){
				val userJoined = user.join()
				val auditLogJoined = auditLog.join(userJoined)
				auditLogsJoined.add(auditLogJoined)
			}
		}
		return auditLogsJoined
	}

	override suspend fun getById(id: UUID): AuditLogJoined? {
		val auditLog = auditLogRepository.findById(id)
		if(auditLog != null){
			val user = userRepository.findById(auditLog.userId)
			if(user != null){
				return auditLog.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(auditLog: AuditLog): AuditLogJoined? {
		val created = auditLogRepository.create(auditLog)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(auditLog: AuditLog): AuditLogJoined? {
		val updated = auditLogRepository.update(auditLog)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return auditLogRepository.delete(id)
	}
}