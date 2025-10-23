package com.internconnect.repository.specification

import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.model.user.User
import java.util.UUID

interface IPasswordResetRepository {
	suspend fun findAll(): List<PasswordReset>
	suspend fun findById(id: UUID): PasswordReset?
	suspend fun create(user: PasswordReset): PasswordReset?
	suspend fun update(user: PasswordReset): PasswordReset?
	suspend fun delete(id: UUID): Boolean
}