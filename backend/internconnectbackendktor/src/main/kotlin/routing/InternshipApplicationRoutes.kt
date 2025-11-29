package com.internconnect.routing

import com.internconnect.service.specification.IEmailVerificationService
import com.internconnect.service.specification.IInternshipApplicationService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.internshipApplicationRoutes() {
	val internshipApplicationService by inject<IInternshipApplicationService>()
	route("/internship-applications") {
		get {
			call.respond(internshipApplicationService.getAll())
		}
	}
}