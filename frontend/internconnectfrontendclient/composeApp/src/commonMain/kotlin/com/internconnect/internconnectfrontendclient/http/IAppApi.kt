package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.response.StudentDto
import com.internconnect.internconnectfrontendclient.data.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.request.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.data.dto.TokenDto

interface IAppApi{
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): String?
	suspend fun login(loginUserDto: LoginUserDto): TokenDto?
	suspend fun forgotPassword(email: String): String?
	suspend fun logout()
	suspend fun refreshToken(): TokenDto?
	suspend fun fetchStudentProfileById(userId: String): StudentDto?
	suspend fun fetchCompanyMemberProfileById(userId: String): CompanyMemberDto?
	suspend fun deleteUser(): String?
}