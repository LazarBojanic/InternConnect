package com.internconnect.internconnectbackendktor.model.raw.auditlog

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import java.time.Instant
import java.util.*

@Serializable
data class AuditLog(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val action: String,
	@Serializable(with = ExposedBlobSerializer::class)
	val metadata: ExposedBlob?,
	val ip: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
) {
	companion object {
		fun createNew(
			userId: UUID,
			action: String,
			metadata: ExposedBlob?,
			ip: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): AuditLog {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return AuditLog(
				id = UUID.randomUUID(),
				userId = userId,
				action = action,
				metadata = metadata,
				ip = ip,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}
