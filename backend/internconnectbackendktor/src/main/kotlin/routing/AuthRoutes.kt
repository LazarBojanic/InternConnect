package com.internconnect.routing

import com.internconnect.service.implementation.AuthService
import io.ktor.server.auth.*
import io.ktor.server.routing.*
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