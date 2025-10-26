package com.internconnect.routing

import com.internconnect.service.implementation.PasswordResetService
import com.internconnect.service.specification.IPasswordResetService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.passwordResetRoutes() {
	val passwordResetService by inject<IPasswordResetService>()
	route("/password-resets") {
		get {
			call.respond(passwordResetService.getAll())
		}
	}
}