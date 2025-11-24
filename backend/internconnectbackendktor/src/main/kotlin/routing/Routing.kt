package com.internconnect.routing

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
	install(Resources)

	routing {

		auditLogRoutes()
		userRoutes()
		emailVerificationRoutes()
		oAuthAccountRoutes()
		companyInvitationRoutes()
		studentRoutes()
		companyMemberRoutes()
		companyRoutes()
		passwordResetRoutes()
		refreshTokenRoutes()
		authRoutes()
	}
}
