package com.internconnect.routing

import com.internconnect.service.implementation.AuditLogService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.auditLogRoutes() {
	val auditLogService by inject<AuditLogService>()
	route("/audit-logs") {
		get {
			call.respond(auditLogService.getAll())
		}
	}
}