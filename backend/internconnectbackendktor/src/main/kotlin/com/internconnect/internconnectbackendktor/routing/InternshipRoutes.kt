package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IInternshipService
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
			val internshipsJoined = internshipService.getAll()
			val internshipDtos = internshipsJoined.map { it.toDto() }
			call.respond(internshipDtos)
		}
	}
}