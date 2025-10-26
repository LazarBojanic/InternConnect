package com.internconnect.routing

import com.internconnect.service.implementation.UserService
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
	val userService by inject<UserService>()
	route("/auth") {
		get {

		}
	}
}