package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.response.StudentDto
import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.data.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.request.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.data.dto.TokenDto
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class AppApi (
	private val appHttpClient: AppHttpClient,
	private val tokenDataStore: ITokenDataStore,
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

	override suspend fun login(loginUserDto: LoginUserDto): TokenDto? {
		val resp: HttpResponse = client.post("/auth/login") {
			setBody(loginUserDto)
		}
		return when (resp.status) {
			HttpStatusCode.OK, HttpStatusCode.Created -> resp.body<TokenDto>()
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

	override suspend fun refreshToken(): TokenDto? {
		val refreshDto = RefreshDto(tokenDataStore.tokenDto.value?.refresh, "composeApp", "0.0.0.0")
		val resp: HttpResponse = client.post("/auth/refresh") {
			setBody(refreshDto)
		}
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<TokenDto>()
			else -> null
		}
	}

	override suspend fun fetchStudentProfileById(userId: String): StudentDto? {
		val resp: HttpResponse = client.get("/student/me/$userId")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<StudentDto>()
			else -> null
		}
	}

	override suspend fun fetchCompanyMemberProfileById(userId: String): CompanyMemberDto? {
		val resp: HttpResponse = client.get("/company-member/me/$userId")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<CompanyMemberDto>()
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