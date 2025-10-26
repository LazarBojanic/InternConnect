package com.internconnect.routing

import com.internconnect.service.implementation.AuditLogService
import com.internconnect.service.specification.IAuditLogService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.auditLogRoutes() {
	val auditLogService by inject<IAuditLogService>()
	route("/audit-logs") {
		get {
			call.respond(auditLogService.getAll())
		}
	}
}