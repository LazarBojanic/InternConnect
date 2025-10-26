package com.internconnect.routing

import com.internconnect.service.implementation.CompanyMemberService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyMemberRoutes() {
	val companyMemberService by inject<CompanyMemberService>()
	route("/company-members") {
		get {
			call.respond(companyMemberService.getAll())
		}
	}
}