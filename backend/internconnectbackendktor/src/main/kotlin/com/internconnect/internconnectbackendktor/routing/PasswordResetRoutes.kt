package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IPasswordResetService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.passwordResetRoutes() {
	val passwordResetService by inject<IPasswordResetService>()
	route("/password-resets") {
		get {

		}
	}
}