package com.internconnect.routing

import com.internconnect.dto.CompanyMemberProfileDto
import com.internconnect.service.specification.IUserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.companyMemberRoutes() {
	val userService by inject<IUserService>()
	authenticate("auth-jwt") {
		route("/company-member/me/{userId}") {
			get{
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val companyProfileDto: CompanyMemberProfileDto? = userService.getCompanyMemberProfileById(UUID.fromString(userId))
				if (companyProfileDto != null) {
					call.respond(HttpStatusCode.OK, companyProfileDto)
				}
			}
		}
	}

}