package com.internconnect.service.implementation

import com.internconnect.model.user.User
import com.internconnect.repository.specification.IUserRepository
import com.internconnect.service.specification.IUserService
import java.util.*

class UserService (
	private val userRepository: IUserRepository,
) : IUserService {
	override suspend fun getAll(): List<User> {
		return userRepository.findAll()
	}

	override suspend fun getById(id: UUID): User? {
		return userRepository.findById(id)
	}
	override suspend fun getByEmail(email: String): User? {
		return userRepository.findByEmail(email)
	}

	override suspend fun create(user: User): User? {
		return userRepository.create(user)
	}

	override suspend fun update(user: User): User? {
		TODO("Not yet implemented")	}

	override suspend fun delete(id: UUID): Boolean {
		return userRepository.delete(id)
	}
}