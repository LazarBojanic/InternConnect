package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyDto
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.dto.Token

interface IAppApi{
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String?
	suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): String?
	suspend fun login(loginUserDto: LoginUserDto): Token?
	suspend fun forgotPassword(email: String): String?
	suspend fun logout()
	suspend fun refreshToken(): String?
	suspend fun getUser(): String?
	suspend fun deleteUser(): String?
}