package com.internconnect.routing

import com.internconnect.model.organizationinvitation.OrganizationInvitationTable
import com.internconnect.service.implementation.OAuthAccountService
import com.internconnect.service.implementation.OrganizationInvitationService
import com.internconnect.service.implementation.OrganizationMemberService
import com.internconnect.service.implementation.OrganizationService
import com.internconnect.service.implementation.PasswordResetService
import com.internconnect.service.implementation.RefreshTokenService
import com.internconnect.service.implementation.UserService
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.refreshTokenRoutes() {
	val refreshTokenService by inject<RefreshTokenService>()
	route("/refresh-tokens") {
		get {
			call.respond(refreshTokenService.getAll())
		}
	}
}