package com.internconnect.routing

import com.internconnect.service.implementation.EmailVerificationService
import com.internconnect.service.specification.IEmailVerificationService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.emailVerificationRoutes() {
	val emailVerificationService by inject<IEmailVerificationService>()
	route("/email-verifications") {
		get {
			call.respond(emailVerificationService.getAll())
		}
	}
}