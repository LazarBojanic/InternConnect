package com.internconnect.http

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
	install(AsyncApiPlugin) {
		extension = AsyncApiExtension.builder {
			info {
				title("Sample API")
				version("1.0.0")
			}
		}
	}
	install(CORS) {
		allowMethod(HttpMethod.Options)
		allowMethod(HttpMethod.Put)
		allowMethod(HttpMethod.Delete)
		allowMethod(HttpMethod.Patch)
		allowMethod(HttpMethod.Post)
		allowMethod(HttpMethod.Get)
		allowHeader(HttpHeaders.Authorization)
		allowHeader("MyCustomHeader")
		anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
	}
	install(DefaultHeaders) {
		header("X-Engine", "Ktor") // will send this header with each response
	}
	routing {
		openAPI(path = "openapi")
	}
	routing {
		swaggerUI(path = "openapi")
	}
}
