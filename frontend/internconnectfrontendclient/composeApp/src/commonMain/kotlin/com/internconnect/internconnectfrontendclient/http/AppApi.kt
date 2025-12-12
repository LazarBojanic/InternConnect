package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.model.dto.TokenDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.InternshipApplicationDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.InternshipDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.StudentDto
import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters

class AppApi (
	private val appHttpClient: AppHttpClient,
	private val tokenDataStore: ITokenDataStore
) : IAppApi{
	private val client get() = appHttpClient.client

	override suspend fun registerStudent(registerStudentDto: RegisterStudentDto): String? {
		val resp: HttpResponse = client.post("/auth/register-student") { setBody(registerStudentDto) }
		return resp.bodyOrNullAsText()
	}

	override suspend fun registerCompanyMember(registerCompanyMemberDto: RegisterCompanyMemberDto): String? {
		val resp: HttpResponse = client.post("/auth/register-company-member") { setBody(registerCompanyMemberDto) }
		return resp.bodyOrNullAsText()
	}

	override suspend fun fetchInternships(): List<InternshipDto>? {
		val resp: HttpResponse = client.get("/internships")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<InternshipDto>>()
			else -> null
		}
	}

	override suspend fun fetchCategories(): List<String>? {
		val resp: HttpResponse = client.get("/internships/categories")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<String>>()
			else -> null
		}
	}

	override suspend fun fetchMyApplications(): List<InternshipApplicationDto>? {
		val resp: HttpResponse = client.get("/student/applications")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<InternshipApplicationDto>>()
			else -> null
		}
	}

	override suspend fun fetchSavedInternships(): List<InternshipDto>? {
		val resp: HttpResponse = client.get("/student/saved-internships")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<InternshipDto>>()
			else -> null
		}
	}

	override suspend fun login(loginUserDto: LoginUserDto): TokenDto? {
		val resp: HttpResponse = client.post("/auth/login") { setBody(loginUserDto) }
		return when (resp.status) {
			HttpStatusCode.OK, HttpStatusCode.Created -> resp.body<TokenDto>()
			else -> null
		}
	}

	override suspend fun forgotPassword(email: String): String? {
		val resp: HttpResponse = client.post("/TODO") { url { parameters.append("email", email) } }
		return resp.bodyOrNullAsText()
	}

	override suspend fun logout(): Boolean {
		val resp: HttpResponse = client.post("/auth/logout")
		return resp.status == HttpStatusCode.OK
	}

	override suspend fun refreshToken(): TokenDto? {
		val tokenDto = tokenDataStore.tokenDto.value
		val resp: HttpResponse = client.post("/auth/refresh") { setBody(tokenDto) }
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<TokenDto>()
			else -> null
		}
	}

	override suspend fun fetchStudentById(userId: String): StudentDto? {
		val resp: HttpResponse = client.get("/student/me/$userId")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<StudentDto>()
			else -> null
		}
	}

	override suspend fun fetchCompanyMemberById(userId: String): CompanyMemberDto? {
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

	// -----------------------------
	// Company-member side (new)
	// -----------------------------
	override suspend fun fetchCompanyInternships(): List<InternshipDto>? {
		val resp: HttpResponse = client.get("/company-member/internships")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<InternshipDto>>()
			else -> null
		}
	}

	override suspend fun postInternship(internship: InternshipDto): String? {
		val resp: HttpResponse = client.post("/company-member/internships") { setBody(internship) }
		return resp.bodyOrNullAsText()
	}

	override suspend fun fetchCandidates(internshipId: String): List<InternshipApplicationDto>? {
		val resp: HttpResponse = client.get("/company-member/internships/$internshipId/applications")
		return when (resp.status) {
			HttpStatusCode.OK -> resp.body<List<InternshipApplicationDto>>()
			else -> null
		}
	}

	override suspend fun updateApplicationStatus(applicationId: String, status: String): String? {
		val resp: HttpResponse = client.put("/company-member/applications/$applicationId/status") {
			url { parameters.append("status", status) }
		}
		return resp.bodyOrNullAsText()
	}

	private suspend inline fun HttpResponse.bodyOrNullAsText(): String? =
		when (status) {
			HttpStatusCode.OK, HttpStatusCode.Created, HttpStatusCode.Accepted -> try { body<String>() } catch (_: Throwable) { null }
			else -> null
		}
}