package com.internconnect.model.passwordauth

import com.internconnect.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

data class PasswordAuth(
	val userId: UUID,
	val encryptedPassword: String,
	val encryptionAlgorithm: String,
	val passwordSetAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
) {
	companion object {
		fun createNew(
			userId: UUID,
			encryptedPassword: String,
			encryptionAlgorithm: String,
			passwordSetAt: Instant = Instant.now(),
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): PasswordAuth {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return PasswordAuth(
				userId = userId,
				encryptedPassword = encryptedPassword,
				encryptionAlgorithm = encryptionAlgorithm,
				passwordSetAt = passwordSetAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}