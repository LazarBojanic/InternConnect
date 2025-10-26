package com.internconnect.routing

import com.internconnect.service.implementation.UserService
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

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
