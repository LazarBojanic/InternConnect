package com.internconnect.service.implementation

import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.repository.specification.IPasswordResetRepository
import com.internconnect.service.specification.IPasswordResetService
import java.util.*

class PasswordResetService (
	private val passwordResetRepository: IPasswordResetRepository,
) : IPasswordResetService {
	override suspend fun getAll(): List<PasswordReset> {
		return passwordResetRepository.findAll()
	}

	override suspend fun getById(id: UUID): PasswordReset? {
		return passwordResetRepository.findById(id)
	}

	override suspend fun create(passwordReset: PasswordReset): PasswordReset? {
		return passwordResetRepository.create(passwordReset)
	}

	override suspend fun update(passwordReset: PasswordReset): PasswordReset? {
		return passwordResetRepository.update(passwordReset)
	}

	override suspend fun delete(id: UUID): Boolean {
		return passwordResetRepository.delete(id)
	}
}