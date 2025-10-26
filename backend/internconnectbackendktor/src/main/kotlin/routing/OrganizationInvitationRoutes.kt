package com.internconnect.routing

import com.internconnect.service.implementation.CompanyInvitationService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyInvitationRoutes() {
	val companyInvitationService by inject<CompanyInvitationService>()
	route("/company-invitations") {
		get {
			call.respond(companyInvitationService.getAll())
		}
	}
}