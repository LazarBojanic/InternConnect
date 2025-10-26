package com.internconnect.service.specification

import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.passwordreset.PasswordReset
import java.util.UUID

interface IPasswordAuthService {
	suspend fun getAll(): List<PasswordAuth>
	suspend fun getById(id: UUID): PasswordAuth?
	suspend fun create(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun update(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun delete(id: UUID): Boolean
}