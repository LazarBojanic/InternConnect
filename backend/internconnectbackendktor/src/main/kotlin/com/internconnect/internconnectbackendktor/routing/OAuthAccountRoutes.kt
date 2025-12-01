package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IOAuthAccountService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.oAuthAccountRoutes() {
	val oAuthAccountService by inject<IOAuthAccountService>()
	route("/oauth-accounts") {
		get {

		}
	}
}