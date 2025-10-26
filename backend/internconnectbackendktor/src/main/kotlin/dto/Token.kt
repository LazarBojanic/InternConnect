package com.internconnect.dto

import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Token(
	val access: String,
	val refresh: String
)