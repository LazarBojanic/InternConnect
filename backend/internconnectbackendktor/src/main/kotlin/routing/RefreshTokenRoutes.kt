package com.internconnect.routing

import com.internconnect.service.implementation.RefreshTokenService
import com.internconnect.service.specification.IRefreshTokenService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.refreshTokenRoutes() {
	val refreshTokenService by inject<IRefreshTokenService>()
	route("/refresh-tokens") {
		get {
			call.respond(refreshTokenService.getAll())
		}
	}
}