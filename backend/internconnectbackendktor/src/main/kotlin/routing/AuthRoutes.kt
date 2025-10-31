package com.internconnect.routing

import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.RefreshDto
import com.internconnect.dto.RegisterCompanyMemberDto
import com.internconnect.dto.RegisterStudentDto
import com.internconnect.service.specification.IAuthService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.authRoutes() {
	val authService by inject<IAuthService>()
	route("/auth/login") {
		post {
			val dto = call.receive<LoginUserDto>()
			val token = authService.login(dto)
			if (token != null) {
				call.respond(HttpStatusCode.OK, token)
			}
			else {
				call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "invalid_credentials"))
			}
		}
	}
	route("/auth/login-google") {
		post {

		}
	}
	authenticate("auth-jwt") {
		route("/auth/logout") {
			post {
				val principal = call.principal<JWTPrincipal>()!!
				authService.logoutCurrentSession(principal)
				call.respond(HttpStatusCode.OK)
			}
		}
		route("/auth/logout-all") {
			post {
				val principal = call.principal<JWTPrincipal>()!!
				val userId = UUID.fromString(principal.payload.subject)
				authService.logoutAllSessions(userId)
				call.respond(HttpStatusCode.OK)
			}
		}
	}
	route("/auth/register-student") {
		post {
			val registerStudentDto = call.receive<RegisterStudentDto>()
			authService.registerStudent(registerStudentDto)
			call.respond(HttpStatusCode.OK, "Registration successful")
		}
	}
	route("/auth/register-company-member") {
		post {
			val registerCompanyMemberDto = call.receive<RegisterCompanyMemberDto>()
			authService.registerCompanyMember(registerCompanyMemberDto)
			call.respond(HttpStatusCode.OK, "Registration successful")
		}
	}
	route("/auth/refresh") {
		post {
			val refreshDto = call.receive<RefreshDto>()
			val token = authService.refresh(refreshDto)
			call.respond(HttpStatusCode.OK, token!!)
		}
	}

}