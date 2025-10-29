package com.internconnect.routing

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun Application.configureRouting() {
	install(Resources)

	routing {
		route("/"){
			get {
				call.respondText("hello world")
			}
		}
		auditLogRoutes()
		userRoutes()
		emailVerificationRoutes()
		oAuthAccountRoutes()
		companyInvitationRoutes()
		companyMemberRoutes()
		companyRoutes()
		passwordResetRoutes()
		refreshTokenRoutes()
		authRoutes()
	}
}
