package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.passwordreset.PasswordReset
import java.util.*

interface IPasswordResetRepository {
	suspend fun findAll(): List<PasswordReset>
	suspend fun findById(id: UUID): PasswordReset?
	suspend fun create(passwordReset: PasswordReset): PasswordReset?
	suspend fun update(passwordReset: PasswordReset): PasswordReset?
	suspend fun delete(id: UUID): Boolean
}