package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.PasswordAuthJoined
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.PasswordAuth
import java.util.*

interface IPasswordAuthService {
	suspend fun getAll(): List<PasswordAuthJoined>
	suspend fun getByUserId(userId: UUID): PasswordAuthJoined?
	suspend fun create(passwordAuth: PasswordAuth): PasswordAuthJoined?
	suspend fun update(passwordAuth: PasswordAuth): PasswordAuthJoined?
	suspend fun delete(id: UUID): Boolean
}