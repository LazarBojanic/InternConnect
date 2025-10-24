package com.internconnect.service.specification

import com.internconnect.model.user.User
import kotlinx.datetime.LocalDateTime
import java.util.UUID

interface IAuthService {
	suspend fun register(user: User): User?
	suspend fun login(user: User): User?
	suspend fun logout(user: User)
	suspend fun issueAccess(userId: UUID, email: String, role: String, orgId: UUID?): String
	suspend fun issueRefresh(sessionId: UUID, userId: UUID): Pair<String, LocalDateTime>
}