package com.internconnect.service.implementation

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.repository.specification.IPasswordAuthRepository
import com.internconnect.service.specification.IPasswordAuthService
import java.util.*

class PasswordAuthService (
	private val passwordAuthRepository: IPasswordAuthRepository,
) : IPasswordAuthService {
	override suspend fun getAll(): List<PasswordAuth> {
		TODO("Not yet implemented")
	}

	override suspend fun getByUserId(userId: UUID): PasswordAuth? {
		TODO("Not yet implemented")
	}

	override suspend fun create(passwordAuth: PasswordAuth): PasswordAuth? {
		TODO("Not yet implemented")
	}

	override suspend fun update(passwordAuth: PasswordAuth): PasswordAuth? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}