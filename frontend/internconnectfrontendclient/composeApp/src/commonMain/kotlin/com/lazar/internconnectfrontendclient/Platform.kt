package com.lazar.internconnectfrontendclient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform