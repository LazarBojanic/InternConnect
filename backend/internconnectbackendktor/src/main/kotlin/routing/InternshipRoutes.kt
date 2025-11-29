package com.internconnect.routing

import com.internconnect.service.specification.IEmailVerificationService
import com.internconnect.service.specification.IInternshipService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.internshipRoutes() {
	val internshipService by inject<IInternshipService>()
	route("/internships") {
		get {
			call.respond(internshipService.getAll())
		}
	}
}