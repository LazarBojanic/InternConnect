package com.internconnect.service.implementation

import com.internconnect.model.user.User
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.service.specification.IAuthService

class AuthService (
	private val userRepository: UserRepository,
) : IAuthService {
	override suspend fun register(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun login(user: User): User? {
		TODO("Not yet implemented")
	}

	override suspend fun logout(user: User) {
		TODO("Not yet implemented")
	}

}