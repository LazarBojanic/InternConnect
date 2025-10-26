package com.internconnect.model.passwordauth

import com.internconnect.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

data class PasswordAuth(
	val userId: String,
	val encryptedPassword: String,
	val encryptionAlgorithm: String,
	val passwordSetAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)