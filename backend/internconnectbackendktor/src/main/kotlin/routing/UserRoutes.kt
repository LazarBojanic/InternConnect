package com.internconnect.routing

import com.internconnect.service.specification.IUserService
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
	val userService by inject<IUserService>()
}