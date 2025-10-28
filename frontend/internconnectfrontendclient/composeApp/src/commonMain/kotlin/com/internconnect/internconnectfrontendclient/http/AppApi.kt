package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyDto
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.dto.Token
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters

class AppApi (
	private val appHttpClient: AppHttpClient,
) : IAppApi{
	private val client get() = appHttpClient.client
	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String? {
		val resp: HttpResponse = client.post("/auth/register/student") {
			setBody(registerStudentDto)
		}
		return resp.bodyOrNullAsText()
	}

	override suspend fun registerCompany(registerCompanyDto: RegisterCompanyDto): String? {
		val resp: HttpResponse = client.post("/auth/register/company") {
			setBody(registerCompanyDto)
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
		val resp: HttpResponse = client.post("/auth/forgot-password") {
			url { parameters.append("email", email) }
		}
		return resp.bodyOrNullAsText()
	}

	override suspend fun logout() {
		client.post("/auth/logout")
	}

	override suspend fun refreshToken(): String? {
		val resp: HttpResponse = client.post("/auth/refresh")
		return resp.bodyOrNullAsText()
	}

	override suspend fun getUser(): String? {
		val resp: HttpResponse = client.get("/users/me")
		return resp.bodyOrNullAsText()
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