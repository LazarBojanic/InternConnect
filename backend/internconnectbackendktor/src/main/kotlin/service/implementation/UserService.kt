package com.internconnect.service.implementation

import com.internconnect.model.user.User
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.repository.specification.IUserRepository
import com.internconnect.service.specification.IUserService
import java.util.UUID

class UserService (
	private val userRepository: UserRepository,
) : IUserService {
	override suspend fun getAll(): List<User> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): User? {
		TODO("Not yet implemented")
	}
	override suspend fun getByEmail(email: String): User? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: User): User? {
		userRepository.create(user)
	}

	override suspend fun update(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}