package com.internconnect.internconnectbackendktor.routing

import com.internconnect.internconnectbackendktor.service.specification.IUserService
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
	val userService by inject<IUserService>()
}