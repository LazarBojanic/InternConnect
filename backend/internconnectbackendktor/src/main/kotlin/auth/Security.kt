package com.internconnect.auth

import com.internconnect.service.specification.IAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.RouteSelector
import io.ktor.server.routing.intercept
import org.koin.ktor.ext.inject
import com.internconnect.model.user.UserRole
import io.ktor.server.routing.RouteSelectorEvaluation
import io.ktor.server.routing.RoutingResolveContext

fun Application.configureSecurity() {
	val authService by inject<IAuthService>()
	install(Authentication) {
		jwt("auth-jwt") {
			verifier(authService.verifier())
			validate { cred ->
				val sub = cred.payload.subject ?: return@validate null
				if (cred.payload.getClaim("typ").asString() == "refresh") return@validate null
				JWTPrincipal(cred.payload)
			}
			challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "unauthorized")) }
		}
	}
}

val RoleAuthorizationPlugin = createRouteScopedPlugin(
	name = "RoleAuthorizationPlugin",
	createConfiguration = ::RoleAuthorizationConfig
) {
	val allowedRoles = pluginConfig.allowedRoles.map { it.name }.toSet()
	onCall { call ->
		val principal = call.principal<JWTPrincipal>()
		val role = principal?.payload?.getClaim("role")?.asString()
		if (role == null || role !in allowedRoles) {
			call.respond(
				HttpStatusCode.Forbidden,
				mapOf("error" to "Access denied. Required roles: $allowedRoles")
			)
			return@onCall
		}
	}
}

class RoleAuthorizationConfig {
	val allowedRoles = mutableListOf<UserRole>()
	fun roles(vararg roles: UserRole) {
		allowedRoles.addAll(roles)
	}
}

fun Route.authorize(vararg roles: UserRole, build: Route.() -> Unit): Route {
	val authorizedRoute = createChild(object : RouteSelector() {
		override suspend fun evaluate(context: RoutingResolveContext, segmentIndex: Int) =
			RouteSelectorEvaluation.Constant
	})
	authorizedRoute.install(RoleAuthorizationPlugin) {
		roles(*roles)
	}
	authorizedRoute.build()
	return authorizedRoute
}
