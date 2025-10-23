package com.internconnect.routing

import com.internconnect.service.implementation.AuthService
import com.internconnect.service.implementation.UserService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
	val authService by inject<AuthService>()
	route("/auth") {
		get {

		}
	}
}