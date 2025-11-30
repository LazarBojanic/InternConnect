package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.PasswordResetJoined
import com.internconnect.internconnectbackendktor.model.raw.passwordreset.PasswordReset
import java.util.*

interface IPasswordResetService {
	suspend fun getAll(): List<PasswordResetJoined>
	suspend fun getById(id: UUID): PasswordResetJoined?
	suspend fun create(passwordReset: PasswordReset): PasswordResetJoined?
	suspend fun update(passwordReset: PasswordReset): PasswordResetJoined?
	suspend fun delete(id: UUID): Boolean
}