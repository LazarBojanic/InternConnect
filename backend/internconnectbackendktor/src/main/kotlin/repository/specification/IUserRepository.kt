package com.internconnect.repository.specification

import com.internconnect.model.user.User
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IUserRepository {
	suspend fun findAll(): List<User>
	suspend fun findById(id: UUID): User?
	suspend fun create(user: User): User?
	suspend fun update(user: User): User?
	suspend fun delete(id: UUID): Boolean
}