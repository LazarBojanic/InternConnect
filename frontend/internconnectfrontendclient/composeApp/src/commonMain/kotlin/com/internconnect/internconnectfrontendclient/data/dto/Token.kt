package com.internconnect.internconnectfrontendclient.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Token(
	val access: String?,
	val refresh: String?
)