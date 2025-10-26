package com.internconnect.routing

import com.internconnect.dto.LoginUserDto
import com.internconnect.model.dto.RegisterCompanyDto
import com.internconnect.model.dto.RegisterStudentDto
import com.internconnect.service.implementation.AuthService
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
	val authService by inject<AuthService>()
	authenticate("auth-jwt"){
		route("/login") {
			post {
				val loginUserDto = call.receive<LoginUserDto>()
				val token = authService.login(loginUserDto)
				if(token != null){
					call.respond(token)
				}
				else{
					call.respond("Invalid credentials")
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
	}
	route("/register-student") {
		post {
			val registerStudentDto = call.receive<RegisterStudentDto>()
			authService.registerStudent(registerStudentDto)
			call.respond("Registration successful")
		}
	}
	route("/register-company") {
		post {
			val registerCompanyDto = call.receive<RegisterCompanyDto>()
			authService.registerCompany(registerCompanyDto)
			call.respond("Registration successful")
		}
	}

}