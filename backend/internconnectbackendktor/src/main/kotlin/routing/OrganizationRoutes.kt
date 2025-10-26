package com.internconnect.routing

import com.internconnect.service.implementation.CompanyService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyRoutes() {
	val companyService by inject<CompanyService>()
	route("/company") {
		get {
			call.respond(companyService.getAll())
		}
	}
}