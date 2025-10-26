package com.internconnect.repository.specification

import com.internconnect.model.passwordauth.PasswordAuth
import java.util.*

interface IPasswordAuthRepository {
	suspend fun findAll(): List<PasswordAuth>
	suspend fun findById(id: UUID): PasswordAuth?
	suspend fun findByUserId(userId: UUID): PasswordAuth?
	suspend fun create(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun update(passwordAuth: PasswordAuth): PasswordAuth?
	suspend fun delete(id: UUID): Boolean
}