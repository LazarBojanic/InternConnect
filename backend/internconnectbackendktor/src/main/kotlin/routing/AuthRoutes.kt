package com.internconnect.routing

import com.internconnect.dto.LoginUserDto
import com.internconnect.dto.RegisterCompanyDto
import com.internconnect.dto.RegisterStudentDto
import com.internconnect.service.implementation.AuthService
import com.internconnect.service.specification.IAuthService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
	val authService by inject<IAuthService>()
	route("/login") {
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
	route("/login-google") {
		post {

		}
	}
	route("/logout") {
		post {

		}
	}
	route("/logout") {
		post {

		}
	}
	route("/register-student") {
		post {
			val registerStudentDto = call.receive<RegisterStudentDto>()
			authService.registerStudent(registerStudentDto)
			call.respond(HttpStatusCode.OK, "Registration successful")
		}
	}
	route("/register-company") {
		post {
			val registerCompanyDto = call.receive<RegisterCompanyDto>()
			authService.registerCompany(registerCompanyDto)
			call.respond(HttpStatusCode.OK, "Registration successful")
		}
	}

}