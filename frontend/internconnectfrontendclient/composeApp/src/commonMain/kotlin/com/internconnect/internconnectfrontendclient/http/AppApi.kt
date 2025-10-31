package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto
import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.dto.Token
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class AppApi (
	private val appHttpClient: AppHttpClient,
) : IAppApi{
	private val client get() = appHttpClient.client
	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String? {
		val resp: HttpResponse = client.post("/auth/register-student") {
			setBody(registerStudentDto)
		}
		return resp.bodyOrNullAsText()
	}

	override suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): String? {
		val resp: HttpResponse = client.post("/auth/register-company-member") {
			setBody(registerCompanyMemberDto)
		}
		return resp.bodyOrNullAsText()
	}

	override suspend fun login(loginUserDto: LoginUserDto): Token? {
		val resp: HttpResponse = client.post("/auth/login") {
			setBody(loginUserDto)
		}
		return when (resp.status) {
			HttpStatusCode.OK, HttpStatusCode.Created -> resp.body<Token>()
			else -> null
		}
	}

	override suspend fun forgotPassword(email: String): String? {
		val resp: HttpResponse = client.post("/TODO") {
			url { parameters.append("email", email) }
		}
		return resp.bodyOrNullAsText()
	}

	override suspend fun logout() {
		client.post("/auth/logout/TODO")
	}

	override suspend fun refreshToken(): String? {
		val resp: HttpResponse = client.post("/auth/refresh")
		return resp.bodyOrNullAsText()
	}

	override suspend fun fetchStudentProfileById(userId: String): StudentProfileDto? {
		val resp: HttpResponse = client.get("/student/me/$userId")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<StudentProfileDto>()
			else -> null
		}
	}

	override suspend fun fetchCompanyMemberProfileById(userId: String): CompanyMemberProfileDto? {
		val resp: HttpResponse = client.get("/company-member/me/$userId")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<CompanyMemberProfileDto>()
			else -> null
		}
	}

	override suspend fun deleteUser(): String? {
		val resp: HttpResponse = client.delete("/users/me")
		return resp.bodyOrNullAsText()
	}

	private suspend inline fun HttpResponse.bodyOrNullAsText(): String? =
		when (status) {
			HttpStatusCode.OK, HttpStatusCode.Created, HttpStatusCode.Accepted -> try {
				body<String>()
			} catch (_: Throwable) {
				null
			}
			else -> null
		}
}