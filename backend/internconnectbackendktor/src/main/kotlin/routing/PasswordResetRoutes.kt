package com.internconnect.routing

import com.internconnect.model.companyinvitation.CompanyInvitationTable
import com.internconnect.service.implementation.OAuthAccountService
import com.internconnect.service.implementation.CompanyInvitationService
import com.internconnect.service.implementation.CompanyMemberService
import com.internconnect.service.implementation.CompanyService
import com.internconnect.service.implementation.PasswordResetService
import com.internconnect.service.implementation.UserService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.passwordResetRoutes() {
	val passwordResetService by inject<PasswordResetService>()
	route("/password-resets") {
		get {
			call.respond(passwordResetService.getAll())
		}
	}
}