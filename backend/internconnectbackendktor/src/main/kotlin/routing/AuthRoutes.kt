package com.internconnect.routing

import com.internconnect.service.implementation.AuthService
import com.internconnect.service.implementation.UserService
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
	val authService by inject<AuthService>()
	authenticate("auth-jwt"){
		route("/login") {
			post {

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

}