package com.internconnect.auth

import com.internconnect.service.specification.IAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
	val authService by inject<IAuthService>()
	install(Authentication) {
		jwt("auth-jwt") {
			verifier(authService.verifier())
			validate { cred ->
				val sub = cred.payload.subject ?: return@validate null
				JWTPrincipal(cred.payload)
			}
			challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "unauthorized")) }
		}
	}
}



