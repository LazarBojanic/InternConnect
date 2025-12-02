package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.model.dto.response.CompanyMemberDto
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationStatus
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.model.toDto
import com.internconnect.internconnectbackendktor.service.specification.ICompanyMemberService
import com.internconnect.internconnectbackendktor.service.specification.IInternshipApplicationService
import com.internconnect.internconnectbackendktor.service.specification.IInternshipService
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.companyMemberRoutes() {
	val companyMemberService by inject<ICompanyMemberService>()
	val internshipService by inject<IInternshipService>()
	val internshipApplicationService by inject<IInternshipApplicationService>()

	authenticate("auth-jwt") {
		// existing: fetch current company member by user id
		route("/company-member/me/{userId}") {
			get {
				val userId = call.parameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val userUUID = UUID.fromString(userId)
				val companyMemberJoined = companyMemberService.getById(userUUID)
				if (companyMemberJoined != null) {
					call.respond(HttpStatusCode.OK, companyMemberJoined.toDto())
				} else {
					call.respond(HttpStatusCode.NotFound)
				}
			}
		}

		// NEW: list internships for the authenticated company member
		route("/company-member/internships") {
			get {
				val principal = call.principal<JWTPrincipal>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
				val userId = principal.payload.subject ?: return@get call.respond(HttpStatusCode.BadRequest)
				val userUUID = try { UUID.fromString(userId) } catch (_: Throwable) { return@get call.respond(HttpStatusCode.BadRequest) }
				val cm = companyMemberService.getById(userUUID) ?: return@get call.respond(HttpStatusCode.NotFound)
				val list = internshipService.getByCompanyId(cm.company.id)
				call.respond(HttpStatusCode.OK, list.map { it.toDto() })
			}

			// Optional: posting creation (body is InternshipDto-compatible raw)
			post {
				// If you already have dedicated posting endpoint elsewhere, you can remove this.
				// Here we accept a DTO-like body and delegate to service (implement as needed).
				call.respond(HttpStatusCode.NotImplemented, "Posting creation to be wired to service/DTO")
			}
		}

		// NEW: list candidates for a specific internship
		route("/company-member/internships/{internshipId}/applications") {
			get {
				val internshipId = call.parameters["internshipId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
				val internshipUUID = try { UUID.fromString(internshipId) } catch (_: Throwable) { return@get call.respond(HttpStatusCode.BadRequest) }
				val apps = internshipApplicationService.getByInternshipId(internshipUUID)
				call.respond(HttpStatusCode.OK, apps.map { it.toDto() })
			}
		}

		// NEW: update application status
		route("/company-member/applications/{applicationId}/status") {
			put {
				val applicationId = call.parameters["applicationId"] ?: return@put call.respond(HttpStatusCode.BadRequest)
				val statusParam = call.request.queryParameters["status"] ?: return@put call.respond(HttpStatusCode.BadRequest)
				val appUUID = try { UUID.fromString(applicationId) } catch (_: Throwable) { return@put call.respond(HttpStatusCode.BadRequest) }

				val current = internshipApplicationService.getById(appUUID) ?: return@put call.respond(HttpStatusCode.NotFound)
				val newStatus = try {
					InternshipApplicationStatus.valueOf(statusParam)
				} catch (_: Throwable) {
					return@put call.respond(HttpStatusCode.BadRequest)
				}

				val updated = internshipApplicationService.update(
					current.toDomain().copy(status = newStatus)
				)
				if (updated == null) return@put call.respond(HttpStatusCode.InternalServerError)
				call.respond(HttpStatusCode.OK, "Status updated")
			}
		}
	}
}