package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.ICompanyService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.companyRoutes() {
	val companyService by inject<ICompanyService>()
	route("/company") {
		get {
			val companiesJoined = companyService.getAll()
			val companyDtos = companiesJoined.map { it.toDto() }
			call.respond(companyDtos)
		}
	}
}