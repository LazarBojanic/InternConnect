package com.internconnect.routing

import com.internconnect.service.implementation.RefreshTokenService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.refreshTokenRoutes() {
	val refreshTokenService by inject<RefreshTokenService>()
	route("/refresh-tokens") {
		get {
			call.respond(refreshTokenService.getAll())
		}
	}
}