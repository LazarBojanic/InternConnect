package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.PasswordAuthJoined
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.PasswordAuth
import com.internconnect.internconnectbackendktor.repository.specification.IPasswordAuthRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IPasswordAuthService
import java.util.*

class PasswordAuthService(
	private val userRepository: IUserRepository,
	private val passwordAuthRepository: IPasswordAuthRepository,
) : IPasswordAuthService {
	override suspend fun getAll(): List<PasswordAuthJoined> {
		val passwordAuths = passwordAuthRepository.findAll()
		val passwordAuthsJoined = mutableListOf<PasswordAuthJoined>()
		for(passwordAuth: PasswordAuth in passwordAuths) {
			val user = userRepository.findById(passwordAuth.userId)
			if(user != null){
				passwordAuthsJoined.add(passwordAuth.join(user.join()))
			}
		}
		return passwordAuthsJoined
	}

	override suspend fun getByUserId(userId: UUID): PasswordAuthJoined? {
		val passwordAuth = passwordAuthRepository.findByUserId(userId)
		if(passwordAuth != null){
			val user = userRepository.findById(passwordAuth.userId)
			if(user != null){
				return passwordAuth.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(passwordAuth: PasswordAuth): PasswordAuthJoined? {
		val created = passwordAuthRepository.create(passwordAuth)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(passwordAuth: PasswordAuth): PasswordAuthJoined? {
		val updated = passwordAuthRepository.update(passwordAuth)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return passwordAuthRepository.delete(id)
	}
}