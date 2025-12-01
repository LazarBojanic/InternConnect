package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.service.specification.ISessionService
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.sessionRoutes() {
	val sessionService by inject<ISessionService>()
	route("/sessions") {
		get {

		}
	}
}