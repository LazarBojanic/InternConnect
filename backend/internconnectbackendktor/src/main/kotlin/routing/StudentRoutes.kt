package com.internconnect.routing

import com.internconnect.dto.StudentProfileDto
import com.internconnect.service.specification.IUserService
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.studentRoutes() {
	val userService by inject<IUserService>()
	authenticate("auth-jwt") {
		route("/student/me/{userId}") {
			get {
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val studentProfileDto: StudentProfileDto? = userService.getStudentProfileById(UUID.fromString(userId))
				if (studentProfileDto != null) {
					call.respond(HttpStatusCode.OK, studentProfileDto)
				}
			}
		}
	}
}