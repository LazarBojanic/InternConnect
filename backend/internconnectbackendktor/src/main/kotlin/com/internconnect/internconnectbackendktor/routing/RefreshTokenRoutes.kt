package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IRefreshTokenService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.refreshTokenRoutes() {
	val refreshTokenService by inject<IRefreshTokenService>()
	route("/refresh-tokens") {
		get {
			val refreshTokensJoined = refreshTokenService.getAll()
			val refreshTokenDtos = refreshTokensJoined.map { it.toDto() }
			call.respond(refreshTokenDtos)
		}
	}
}