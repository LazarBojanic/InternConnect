package com.internconnect.service.specification

import com.auth0.jwt.JWTVerifier
import com.internconnect.dto.LoginUserDto
import com.internconnect.model.dto.RegisterStudentDto
import com.internconnect.model.user.User
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User?
	fun login(loginUserDto: LoginUserDto): User?
	fun logout(user: User)
	fun issueAccess(userId: UUID, email: String, role: String, orgId: UUID?): String
	fun issueRefresh(sessionId: UUID, userId: UUID): String
	fun verifier(): JWTVerifier
}