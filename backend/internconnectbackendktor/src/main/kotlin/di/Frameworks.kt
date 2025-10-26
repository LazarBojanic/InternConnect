package com.internconnect.di

import com.internconnect.auth.JwtConfig
import com.internconnect.di.serviceModule
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
	install(Koin) {
		slf4jLogger()
		modules(module {
			module { single { JwtConfig.from(environment.config) }}
			repositoryModule
			serviceModule
		})
	}
}
