package com.internconnect.service.specification

import com.internconnect.model.user.User
import kotlinx.datetime.LocalDateTime
import java.util.Date
import java.util.UUID

interface IAuthService {
	fun register(user: User): User?
	fun login(user: User): User?
	fun logout(user: User)
	fun issueAccess(userId: UUID, email: String, role: String, orgId: UUID?): String
	fun issueRefresh(sessionId: UUID, userId: UUID): Pair<String, Date>
	fun verifier(): com.auth0.jwt.JWTVerifier
}