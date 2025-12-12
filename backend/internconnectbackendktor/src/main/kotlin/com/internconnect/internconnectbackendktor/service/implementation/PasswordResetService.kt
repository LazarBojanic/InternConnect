package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.PasswordResetJoined
import com.internconnect.internconnectbackendktor.model.raw.passwordreset.PasswordReset
import com.internconnect.internconnectbackendktor.repository.specification.IPasswordResetRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IPasswordResetService
import java.util.*

class PasswordResetService(
	private val userRepository: IUserRepository,
	private val passwordResetRepository: IPasswordResetRepository,
) : IPasswordResetService {
	override suspend fun getAll(): List<PasswordResetJoined> {
		val passwordResets = passwordResetRepository.findAll()
		val passwordResetsJoined = mutableListOf<PasswordResetJoined>()
		for(passwordReset: PasswordReset in passwordResets) {
			val user = userRepository.findById(passwordReset.userId)
			if(user != null){
				passwordResetsJoined.add(passwordReset.join(user.join()))
			}
		}
		return passwordResetsJoined
	}

	override suspend fun getById(id: UUID): PasswordResetJoined? {
		val passwordReset = passwordResetRepository.findById(id)
		if(passwordReset != null){
			val user = userRepository.findById(passwordReset.userId)
			if(user != null){
				return passwordReset.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(passwordReset: PasswordReset): PasswordResetJoined? {
		val created = passwordResetRepository.create(passwordReset)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(passwordReset: PasswordReset): PasswordResetJoined? {
		val updated = passwordResetRepository.update(passwordReset)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return passwordResetRepository.delete(id)
	}
}