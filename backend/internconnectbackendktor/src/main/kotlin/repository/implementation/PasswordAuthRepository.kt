package com.internconnect.repository.implementation

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.repository.specification.IPasswordAuthRepository
import java.util.*

class PasswordAuthRepository : IPasswordAuthRepository {
	override suspend fun findAll(): List<PasswordAuth> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): PasswordAuth? {
		TODO("Not yet implemented")
	}

	override suspend fun findByUserId(userId: UUID): PasswordAuth? {
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