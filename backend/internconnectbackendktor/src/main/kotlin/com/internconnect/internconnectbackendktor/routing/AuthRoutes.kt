package com.internconnect.internconnectbackendktor.routing


import com.internconnect.internconnectbackendktor.model.dto.response.TokenDto
import com.internconnect.internconnectbackendktor.model.dto.request.LoginUserDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectbackendktor.model.dto.request.RegisterStudentDto
import com.internconnect.internconnectbackendktor.service.specification.IAuthService
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.authRoutes() {
	val authService by inject<IAuthService>()
	route("/auth/login") {
		post {
			val loginUserDto = call.receive<LoginUserDto>()
			val tokenDto = authService.login(loginUserDto)
			if (tokenDto != null) {
				call.respond(HttpStatusCode.OK, tokenDto)
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

	}
	route("/auth/register-student") {
		post {
			val registerStudentDto = call.receive<RegisterStudentDto>()
			val userDto = authService.registerStudent(registerStudentDto)
			if(userDto != null){
				call.respond(HttpStatusCode.OK, "Registration successful")
			}
			else{
				call.respond(HttpStatusCode.BadRequest, "Registration failed")
			}
		}
	}
	route("/auth/register-company-member") {
		post {
			val registerCompanyMemberDto = call.receive<RegisterCompanyMemberDto>()
			val userDto = authService.registerCompanyMember(registerCompanyMemberDto)
			if(userDto != null){
				call.respond(HttpStatusCode.OK, "Registration successful")
			}
			else{
				call.respond(HttpStatusCode.BadRequest, "Registration failed")
			}
		}
	}
	route("/auth/refresh") {
		post {
			val tokenRequestDto = call.receive<TokenDto>()
			val tokenResponseDto = authService.refresh(tokenRequestDto)
			if(tokenResponseDto != null){
				call.respond(HttpStatusCode.OK, tokenResponseDto)
			}
			else{
				call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "invalid_token"))
			}
		}
	}

}