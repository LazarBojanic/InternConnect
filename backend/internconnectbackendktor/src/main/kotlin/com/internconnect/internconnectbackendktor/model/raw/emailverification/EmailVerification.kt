package com.internconnect.internconnectbackendktor.model.raw.emailverification

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class EmailVerification(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val codeHash: String,
	val sentToEmail: String,
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
			sentToEmail: String,
			expiresAt: Instant,
			consumedAt: Instant?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): EmailVerification{
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return EmailVerification(
				id = UUID.randomUUID(),
				userId = userId,
				codeHash = codeHash,
				sentToEmail = sentToEmail,
				expiresAt = expiresAt,
				consumedAt = consumedAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}