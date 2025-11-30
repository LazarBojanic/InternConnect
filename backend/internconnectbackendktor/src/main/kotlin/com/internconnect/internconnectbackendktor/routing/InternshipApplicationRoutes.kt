package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IInternshipApplicationService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID
import kotlin.getValue

fun Route.internshipApplicationRoutes() {
	val internshipApplicationService by inject<IInternshipApplicationService>()
	route("/internship-applications/get-by-student-id/{studentId}") {
		get {
			val studentId = call.parameters["studentId"] ?: return@get
			val studentIdUUID = UUID.fromString(studentId)
			val internshipApplicationsJoined = internshipApplicationService.getByStudentId(studentIdUUID)
			val internshipApplicationDtos = internshipApplicationsJoined.map { it.toDto() }
			call.respond(internshipApplicationDtos)
		}
	}
	route("/internship-applications/get-by-internship-id/{internshipId}") {
		get {
			val internshipId = call.parameters["internshipId"] ?: return@get
			val internshipUUID = UUID.fromString(internshipId)
			val internshipApplicationsJoined = internshipApplicationService.getByInternshipId(internshipUUID)
			val internshipApplicationDtos = internshipApplicationsJoined.map { it.toDto() }
			call.respond(internshipApplicationDtos)
		}
	}
}