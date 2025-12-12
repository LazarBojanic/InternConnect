package com.internconnect.internconnectbackendktor.model.raw.passwordreset

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class PasswordReset(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val codeHash: String,
	@Serializable(with = InstantSerializer::class)
	val expiresAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val consumedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object{
		fun createNew(
			userId: UUID,
			codeHash: String,
			expiresAt: Instant,
			consumedAt: Instant?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): PasswordReset {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return PasswordReset(
				id = UUID.randomUUID(),
				userId = userId,
				codeHash = codeHash,
				expiresAt = expiresAt,
				consumedAt = consumedAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}