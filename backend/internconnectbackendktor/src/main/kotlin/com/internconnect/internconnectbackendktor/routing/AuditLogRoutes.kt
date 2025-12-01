package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.IAuditLogService
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.auditLogRoutes() {
	val auditLogService by inject<IAuditLogService>()
	route("/audit-logs") {
		get {

		}
	}
}