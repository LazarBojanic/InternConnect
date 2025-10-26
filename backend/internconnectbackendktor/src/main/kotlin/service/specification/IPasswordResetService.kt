package com.internconnect.service.specification

import com.internconnect.model.passwordreset.PasswordReset
import java.util.*

interface IPasswordResetService {
	suspend fun getAll(): List<PasswordReset>
	suspend fun getById(id: UUID): PasswordReset?
	suspend fun create(user: PasswordReset): PasswordReset?
	suspend fun update(user: PasswordReset): PasswordReset?
	suspend fun delete(id: UUID): Boolean
}