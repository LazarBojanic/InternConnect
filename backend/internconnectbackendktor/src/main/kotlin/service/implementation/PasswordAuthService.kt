package com.internconnect.service.implementation

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.repository.implementation.PasswordAuthRepository
import com.internconnect.repository.implementation.PasswordResetRepository
import com.internconnect.repository.specification.IPasswordAuthRepository
import com.internconnect.service.specification.IPasswordAuthService
import com.internconnect.service.specification.IPasswordResetService
import java.util.UUID

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