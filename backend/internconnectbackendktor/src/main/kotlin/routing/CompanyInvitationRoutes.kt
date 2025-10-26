package com.internconnect.routing

import com.internconnect.service.specification.ICompanyInvitationService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyInvitationRoutes() {
	val companyInvitationService by inject<ICompanyInvitationService>()
	route("/company-invitations") {
		get {
			call.respond(companyInvitationService.getAll())
		}
	}
}