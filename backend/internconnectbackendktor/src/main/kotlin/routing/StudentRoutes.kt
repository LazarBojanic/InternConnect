package com.internconnect.routing

import com.internconnect.dto.CompanyMemberProfileDto
import com.internconnect.dto.StudentProfileDto
import com.internconnect.service.specification.IUserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID
import kotlin.getValue

fun Route.studentRoutes() {
	val userService by inject<IUserService>()
	authenticate("auth-jwt") {
		route("/student/me/{userId}") {
			get{
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val studentProfileDto: StudentProfileDto? = userService.getStudentProfileById(UUID.fromString(userId))
				if (studentProfileDto != null) {
					call.respond(HttpStatusCode.OK, studentProfileDto)
				}
			}
		}
	}
}