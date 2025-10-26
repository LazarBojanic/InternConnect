package com.internconnect.service.specification

import com.internconnect.model.user.User
import java.util.*

interface IUserService {
	suspend fun getAll(): List<User>
	suspend fun getById(id: UUID): User?
	suspend fun getByEmail(email: String): User?
	suspend fun create(user: User): User?
	suspend fun update(user: User): User?
	suspend fun delete(id: UUID): Boolean
}