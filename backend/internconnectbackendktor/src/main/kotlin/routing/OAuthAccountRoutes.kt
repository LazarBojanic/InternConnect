package com.internconnect.routing

import com.internconnect.service.implementation.OAuthAccountService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.oAuthAccountRoutes() {
	val oAuthAccountService by inject<OAuthAccountService>()
	route("/oauth-accounts") {
		get {
			call.respond(oAuthAccountService.getAll())
		}
	}
}