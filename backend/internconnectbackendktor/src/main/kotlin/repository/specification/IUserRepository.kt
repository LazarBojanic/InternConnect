package com.internconnect.repository.specification

import com.internconnect.model.user.User
import java.util.*

interface IUserRepository {
	suspend fun findAll(): List<User>
	suspend fun findById(id: UUID): User?
	suspend fun findByEmail(email: String): User?
	suspend fun create(user: User): User?
	suspend fun update(user: User): User?
	suspend fun delete(id: UUID): Boolean
}