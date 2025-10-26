package com.internconnect.service.implementation

import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.repository.specification.IPasswordResetRepository
import com.internconnect.service.specification.IPasswordResetService
import java.util.*

class PasswordResetService (
	private val passwordResetRepository: IPasswordResetRepository,
) : IPasswordResetService {
	override suspend fun getAll(): List<PasswordReset> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): PasswordReset? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: PasswordReset): PasswordReset? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: PasswordReset): PasswordReset? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}