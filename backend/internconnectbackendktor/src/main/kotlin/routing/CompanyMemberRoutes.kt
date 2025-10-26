package com.internconnect.routing

import com.internconnect.service.specification.ICompanyMemberService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyMemberRoutes() {
	val companyMemberService by inject<ICompanyMemberService>()
	route("/company-members") {
		get {
			call.respond(companyMemberService.getAll())
		}
	}
}