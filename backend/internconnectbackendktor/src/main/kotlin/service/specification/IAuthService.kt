package com.internconnect.service.specification

import com.auth0.jwt.JWTVerifier
import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.Token
import com.internconnect.model.dto.RegisterCompanyDto
import com.internconnect.model.dto.RegisterStudentDto
import com.internconnect.model.user.User
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): User?
	suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): User?
	suspend fun login(loginUserDto: LoginUserDto): Token?
	suspend fun logout(user: User)
	suspend fun issueAccess(userId: UUID, email: String, userRole: String, companyId: UUID?, sessionId: UUID?): String?
	suspend fun issueRefresh(userId: UUID, sessionId: UUID): String?
	suspend fun verifier(): JWTVerifier?
	suspend fun sha256(input: String): String?
}