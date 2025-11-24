package com.internconnect.service.specification

import com.auth0.jwt.JWTVerifier
import com.internconnect.dto.*
import com.internconnect.model.user.User
import io.ktor.server.auth.jwt.*
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): User?
	suspend fun login(loginUserDto: LoginUserDto): Token?
	suspend fun logoutCurrentSession(principal: JWTPrincipal): Boolean
	suspend fun logoutAllSessions(userId: UUID): Boolean
	fun issueAccess(userId: UUID, email: String, userRole: String, companyId: UUID?, sessionId: UUID?): String?
	fun issueRefresh(userId: UUID, email: String, userRole: String, companyId: UUID?, sessionId: UUID?): String?
	fun verifier(): JWTVerifier
	fun sha256(input: String): String?
	suspend fun refresh(refreshDto: RefreshDto): Token?
}