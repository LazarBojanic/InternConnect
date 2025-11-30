package com.internconnect.internconnectbackendktor.service.specification

import com.auth0.jwt.JWTVerifier
import com.internconnect.internconnectbackendktor.model.dto.request.LoginUserDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterStudentDto
import com.internconnect.internconnectbackendktor.model.dto.TokenDto
import com.internconnect.internconnectbackendktor.model.dto.response.UserDto
import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import io.ktor.server.auth.jwt.*
import java.time.Instant
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): UserJoined?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): UserJoined?
	suspend fun login(loginUserDto: LoginUserDto): TokenDto?
	suspend fun logoutCurrentSession(principal: JWTPrincipal): Boolean
	suspend fun logoutAllSessions(userId: UUID): Boolean
	fun issueAccess(userId: UUID, email: String, role: String, companyId: UUID?, sessionId: UUID?, time: Instant?): String?
	fun issueRefresh(userId: UUID, email: String, role: String, companyId: UUID?, sessionId: UUID?, time: Instant?): String?
	fun verifier(): JWTVerifier
	fun sha256(input: String): String?
	suspend fun refresh(tokenDto: TokenDto): TokenDto?
}