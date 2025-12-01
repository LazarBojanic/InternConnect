package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.dto.response.CompanyMemberDto
import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.ICompanyMemberService
import com.internconnect.internconnectbackendktor.service.specification.IUserService
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.companyMemberRoutes() {
	val companyMemberService by inject<ICompanyMemberService>()
	authenticate("auth-jwt") {
		route("/company-member/me/{userId}") {
			get {
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val userUUID = UUID.fromString(userId)
				val companyMemberJoined = companyMemberService.getById(userUUID)
				if (companyMemberJoined != null) {
					call.respond(HttpStatusCode.OK, companyMemberJoined.toDto())
				}
				else{
					call.respond(HttpStatusCode.NotFound)
				}
			}
		}
	}

}