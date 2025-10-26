package com.internconnect.service.specification

import com.auth0.jwt.JWTVerifier
import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.Token
import com.internconnect.dto.RegisterCompanyDto
import com.internconnect.dto.RegisterStudentDto
import com.internconnect.model.user.User
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User?
	suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): User?
	suspend fun login(loginUserDto: LoginUserDto): Token?
	suspend fun logout(user: User)
	fun issueAccess(userId: UUID, email: String, userRole: String, companyId: UUID?, sessionId: UUID?): String?
	fun issueRefresh(userId: UUID, sessionId: UUID): String?
	fun verifier(): JWTVerifier
	fun sha256(input: String): String?
}