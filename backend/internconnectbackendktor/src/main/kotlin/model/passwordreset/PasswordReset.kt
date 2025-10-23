package com.internconnect.model.passwordreset

import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.UUID
@Serializable
data class PasswordReset(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val codeHash: String,
	val expiresAt: LocalDateTime,
	val consumedAt: LocalDateTime?,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)