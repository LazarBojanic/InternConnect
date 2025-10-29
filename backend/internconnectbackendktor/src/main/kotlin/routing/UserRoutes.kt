package com.internconnect.routing

import com.internconnect.service.implementation.UserService
import com.internconnect.service.specification.IUserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
	val userService by inject<IUserService>()
	authenticate("auth-jwt") {
		route("/me") {
			get{
				val principal = call.principal<JWTPrincipal>()
				call.respond(HttpStatusCode.OK, "TODO")
			}
		}
	}
}