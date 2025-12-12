package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import com.internconnect.internconnectbackendktor.model.raw.user.User
import java.util.*

interface IUserService {
	suspend fun getAll(): List<UserJoined>
	suspend fun getById(id: UUID): UserJoined?
	suspend fun getByEmail(email: String): UserJoined?
	suspend fun create(user: User): UserJoined?
	suspend fun update(user: User): UserJoined?
	suspend fun delete(id: UUID): Boolean
}