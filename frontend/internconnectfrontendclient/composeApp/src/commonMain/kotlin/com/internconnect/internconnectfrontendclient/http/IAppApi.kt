package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto
import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.dto.Token

interface IAppApi{
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): String?
	suspend fun login(loginUserDto: LoginUserDto): Token?
	suspend fun forgotPassword(email: String): String?
	suspend fun logout()
	suspend fun refreshToken(): String?
	suspend fun fetchStudentProfileById(userId: String): StudentProfileDto?
	suspend fun fetchCompanyMemberProfileById(userId: String): CompanyMemberProfileDto?
	suspend fun deleteUser(): String?
}