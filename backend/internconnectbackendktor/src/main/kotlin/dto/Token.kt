package com.internconnect.dto

import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class Token(
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val sessionId: UUID,
	val access: String,
	val refresh: String
)