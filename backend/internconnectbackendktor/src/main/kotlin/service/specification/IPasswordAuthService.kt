package com.internconnect.service.specification

import com.internconnect.model.passwordauth.PasswordAuth
import java.util.*

interface IPasswordAuthService {
	suspend fun getAll(): List<PasswordAuth>
	suspend fun getByUserId(userId: UUID): PasswordAuth?
	suspend fun create(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun update(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun delete(id: UUID): Boolean
}