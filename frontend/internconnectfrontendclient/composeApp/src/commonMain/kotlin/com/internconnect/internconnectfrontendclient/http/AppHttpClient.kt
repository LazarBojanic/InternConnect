package com.internconnect.internconnectfrontendclient.http

import com.internconnect.internconnectfrontendclient.data.store.ITokenStore
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.accept
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class AppHttpClient(
	val baseUrl: String,
	val platformEngine: HttpClientEngineFactory<*>,
	private val tokenStore: ITokenStore
) {
	val client: HttpClient = HttpClient(platformEngine) {
		install(ContentNegotiation) {
			json(
				Json {
					ignoreUnknownKeys = true
					isLenient = true
					explicitNulls = false
					prettyPrint = true
				}
			)
		}
		install(Logging) {
			logger = Logger.DEFAULT
			level = LogLevel.INFO
		}
		install(HttpTimeout) {
			requestTimeoutMillis = 30_000
			connectTimeoutMillis = 30_000
			socketTimeoutMillis = 30_000
		}
		install(DefaultRequest) {
			url {
				takeFrom(baseUrl)
			}
			contentType(ContentType.Application.Json)
			accept(ContentType.Application.Json)

			val token = tokenStore.token.value
			if (!token?.access.isNullOrBlank()) {
				headers.append(HttpHeaders.Authorization, "Bearer $token")
			}
		}
	}
}