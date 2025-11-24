package com.internconnect.routing

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureStaticRouting() {
	routing {
		staticResources("/", "static", index = "index.html")
		staticResources("/privacy-policy", "static/privacy-policy", index = "index.html")
	}
}