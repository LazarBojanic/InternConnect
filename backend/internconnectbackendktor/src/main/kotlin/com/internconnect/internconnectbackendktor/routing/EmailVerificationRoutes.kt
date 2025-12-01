package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IEmailVerificationService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.emailVerificationRoutes() {
	val emailVerificationService by inject<IEmailVerificationService>()
	route("/email-verifications") {
		get {

		}
	}
}