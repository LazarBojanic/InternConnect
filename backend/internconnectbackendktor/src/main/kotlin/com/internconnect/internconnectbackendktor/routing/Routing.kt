package com.internconnect.internconnectbackendktor.routing

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
	install(Resources)

	routing {
		userRoutes()
		studentRoutes()
		companyMemberRoutes()
		companyRoutes()
		internshipRoutes()
		internshipApplicationRoutes()
		authRoutes()
	}
}
