package com.internconnect.service.implementation

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.repository.specification.IPasswordAuthRepository
import com.internconnect.service.specification.IPasswordAuthService
import java.util.*

class PasswordAuthService (
	private val passwordAuthRepository: IPasswordAuthRepository,
) : IPasswordAuthService {
	override suspend fun getAll(): List<PasswordAuth> {
		return passwordAuthRepository.findAll()
	}

	override suspend fun getByUserId(userId: UUID): PasswordAuth? {
		return passwordAuthRepository.findByUserId(userId)
	}

	override suspend fun create(passwordAuth: PasswordAuth): PasswordAuth? {
		return passwordAuthRepository.create(passwordAuth)
	}

	override suspend fun update(passwordAuth: PasswordAuth): PasswordAuth? {
		return passwordAuthRepository.update(passwordAuth)
	}

	override suspend fun delete(id: UUID): Boolean {
		return passwordAuthRepository.delete(id)
	}
}