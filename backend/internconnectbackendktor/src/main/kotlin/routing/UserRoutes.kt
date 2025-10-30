package com.internconnect.routing

import com.internconnect.service.specification.IUserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.userRoutes() {
	val userService by inject<IUserService>()
	authenticate("auth-jwt") {
		route("/users/student/me") {
			get{
				val principal = call.principal<JWTPrincipal>()
				val userId: UUID = UUID.fromString(principal!!.payload.subject)
				val studentProfileDto = userService.getStudentProfileById(userId)
				if (studentProfileDto != null) {
					call.respond(HttpStatusCode.OK, studentProfileDto)
				}
			}
		}
		route("/users/company-member/me") {
			get{
				val principal = call.principal<JWTPrincipal>()
				val userId: UUID = UUID.fromString(principal!!.payload.subject)
				val studentProfileDto = userService.getCompanyMemberProfileById(userId)
				if (studentProfileDto != null) {
					call.respond(HttpStatusCode.OK, studentProfileDto)
				}
			}
		}
	}
}