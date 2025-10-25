package com.internconnect.auth

import com.internconnect.service.implementation.AuthService
import com.internconnect.service.implementation.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Application.configureSecurity() {
	val jwtAlg = environment.config.property("jwt.alg").getString()
	val jwtSecret = environment.config.property("jwt.secret").getString()
	val jwtIss = environment.config.property("jwt.iss").getString()
	val jwtAud = environment.config.property("jwt.aud").getString()
	val authService by inject<AuthService>()
	install(Authentication) {
		jwt("auth-jwt") {
			//TODO env variable
			verifier(authService.verifier())
			validate { cred ->
				//TODO sub variable
				val sub = cred.payload.subject ?: return@validate null
				JWTPrincipal(cred.payload)
			}
			challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "unauthorized")) }
		}
	}
}

