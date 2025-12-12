package com.internconnect.internconnectbackendktor.service.specification

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.internconnect.internconnectbackendktor.model.TokenClaims
import com.internconnect.internconnectbackendktor.model.dto.request.LoginUserDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterStudentDto
import com.internconnect.internconnectbackendktor.model.dto.response.TokenDto
import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import io.ktor.server.auth.jwt.*
import java.util.*

interface IAuthService {
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): UserJoined?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): UserJoined?
	suspend fun login(loginUserDto: LoginUserDto): TokenDto?
	suspend fun logoutCurrentSession(principal: JWTPrincipal): Boolean
	fun issueJWT(tokenClaims: TokenClaims): String?
	fun verifier(): JWTVerifier
	fun sha256(input: String): String?
	suspend fun refresh(tokenDto: TokenDto): TokenDto?
}