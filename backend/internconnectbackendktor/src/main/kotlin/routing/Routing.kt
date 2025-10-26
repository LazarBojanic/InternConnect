package com.internconnect.routing

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
	install(Resources)
	routing {
		auditLogRoutes()
		authRoutes()
		emailVerificationRoutes()
		oAuthAccountRoutes()
		companyInvitationRoutes()
		companyMemberRoutes()
		companyRoutes()
		passwordResetRoutes()
		refreshTokenRoutes()
		userRoutes()
	}
}
