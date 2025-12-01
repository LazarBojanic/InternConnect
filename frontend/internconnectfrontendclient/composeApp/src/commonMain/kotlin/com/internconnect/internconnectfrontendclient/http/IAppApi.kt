package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.model.dto.TokenDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.StudentDto


interface IAppApi{
	suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String?
	suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): String?
	suspend fun login(loginUserDto: LoginUserDto): TokenDto?
	suspend fun forgotPassword(email: String): String?
	suspend fun logout()
	suspend fun refreshToken(): TokenDto?
	suspend fun fetchStudentById(userId: String): StudentDto?
	suspend fun fetchCompanyMemberById(userId: String): CompanyMemberDto?
	suspend fun deleteUser(): String?
}