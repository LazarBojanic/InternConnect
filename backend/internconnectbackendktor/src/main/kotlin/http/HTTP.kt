package com.internconnect.http

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.forwardedheaders.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.slf4j.event.Level

fun Application.configureHTTP() {
	install(CORS) {
		allowMethod(HttpMethod.Options)
		allowMethod(HttpMethod.Put)
		allowMethod(HttpMethod.Delete)
		allowMethod(HttpMethod.Patch)
		allowMethod(HttpMethod.Post)
		allowMethod(HttpMethod.Get)
		allowHeader(HttpHeaders.Authorization)
	}
	install(ForwardedHeaders)
	install(XForwardedHeaders)

	install(CallLogging){
		mdc("rid") {
			call -> call.callId
		}

		level = Level.INFO

		format { call ->

			val rid = call.callId

			"${call.request.httpMethod.value} ${call.request.uri} rid=$rid ua=${call.request.headers["User-Agent"]}"
		}
	}
	install(DefaultHeaders) {
		header("X-Engine", "Ktor")
	}
	routing {
		openAPI(path = "openapi")
	}
	routing {
		swaggerUI(path = "openapi")
	}
}
