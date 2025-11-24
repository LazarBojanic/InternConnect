package com.internconnect.http

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureHTTP() {
	install(CORS) {
		allowMethod(HttpMethod.Options)
		allowMethod(HttpMethod.Put)
		allowMethod(HttpMethod.Delete)
		allowMethod(HttpMethod.Patch)
		allowMethod(HttpMethod.Post)
		allowMethod(HttpMethod.Get)
		allowHeader(HttpHeaders.Authorization)
		anyHost()
	}
	install(DefaultHeaders) {
		header("X-Engine", "Ktor")
	}
}
