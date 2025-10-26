package com.internconnect.routing

import com.internconnect.service.specification.ICompanyService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyRoutes() {
	val companyService by inject<ICompanyService>()
	route("/company") {
		get {
			call.respond(companyService.getAll())
		}
	}
}