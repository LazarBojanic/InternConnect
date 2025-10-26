package com.internconnect.repository.implementation

import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.repository.specification.IPasswordResetRepository
import java.util.*

class PasswordResetRepository : IPasswordResetRepository {
	override suspend fun findAll(): List<PasswordReset> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): PasswordReset? {
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