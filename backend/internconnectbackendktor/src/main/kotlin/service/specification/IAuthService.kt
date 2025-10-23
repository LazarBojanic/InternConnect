package com.internconnect.service.specification

import com.internconnect.model.user.User

interface IAuthService {
	suspend fun register(user: User): User?
	suspend fun login(user: User): User?
	suspend fun logout(user: User)
}