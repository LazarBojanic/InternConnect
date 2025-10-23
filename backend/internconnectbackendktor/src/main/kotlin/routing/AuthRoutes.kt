package com.internconnect.routing

import com.internconnect.service.implementation.UserService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
	val userService by inject<UserService>()
	route("/users") {
		get {
			call.respond(userService.getAll())
		}
	}
}