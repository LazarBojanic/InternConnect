package com.internconnect.routing

import com.internconnect.service.specification.IOAuthAccountService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.oAuthAccountRoutes() {
	val oAuthAccountService by inject<IOAuthAccountService>()
	route("/oauth-accounts") {
		get {
			call.respond(oAuthAccountService.getAll())
		}
	}
}