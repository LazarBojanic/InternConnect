package com.internconnect.repository.implementation

import com.internconnect.model.user.User
import com.internconnect.repository.specification.IUserRepository
import java.util.*

class UserRepository : IUserRepository {
	override suspend fun findAll(): List<User> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): User? {
		TODO("Not yet implemented")
	}

	override suspend fun findByEmail(email: String): User? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}