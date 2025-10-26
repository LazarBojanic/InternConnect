package com.internconnect.repository.specification

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.passwordreset.PasswordReset
import java.util.UUID

interface IPasswordAuthRepository {
	suspend fun findAll(): List<PasswordAuth>
	suspend fun findById(id: UUID): PasswordAuth?
	suspend fun create(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun update(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun delete(id: UUID): Boolean
}