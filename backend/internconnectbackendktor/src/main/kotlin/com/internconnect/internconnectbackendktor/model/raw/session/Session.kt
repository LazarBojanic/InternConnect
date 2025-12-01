package com.internconnect.internconnectbackendktor.model.raw.session

import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID
@Serializable
data class Session(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val ip: String?,
	val userAgent: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object{
		fun createNew(
			userId: UUID,
			ip: String?,
			userAgent: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		) : Session{
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return Session(UUID.randomUUID(),
				userId,
				ip,
				userAgent,
				createdAt,
				updatedAt
			)
		}
	}
}