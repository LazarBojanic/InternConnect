package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.dto.response.StudentDto
import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IStudentService
import com.internconnect.internconnectbackendktor.service.specification.IUserService
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.studentRoutes() {
	val studentService by inject<IStudentService>()
	authenticate("auth-jwt") {
		route("/student/me/{userId}") {
			get {
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val userUUID = UUID.fromString(userId)
				val studentJoined = studentService.getById(userUUID)
				if (studentJoined != null) {
					call.respond(HttpStatusCode.OK, studentJoined.toDto())
				}
				else{
					call.respond(HttpStatusCode.NotFound)
				}
			}
		}
	}
}