package com.internconnect.model.passwordauth

import com.internconnect.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

data class PasswordAuth(
	val userId: UUID,
	val encryptedPassword: String,
	val encryptionAlgorithm: String,
	val passwordSetAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			userId: UUID,
			encryptedPassword: String,
			encryptionAlgorithm: String,
		): PasswordAuth {
			return PasswordAuth(
				userId = userId,
				encryptedPassword = encryptedPassword,
				encryptionAlgorithm = encryptionAlgorithm,
				passwordSetAt = Instant.now(),
				createdAt = Instant.now(),
				updatedAt = Instant.now()
			)
		}
	}
}