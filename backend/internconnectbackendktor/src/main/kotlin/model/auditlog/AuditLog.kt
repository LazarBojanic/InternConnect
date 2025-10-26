package com.internconnect.model.auditlog

import com.internconnect.util.InetAddressSerializer
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.net.InetAddress
import java.time.Instant
import java.util.*

@Serializable
data class AuditLog(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val action: String,
	val metadata: Metadata?,
	val ip: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			userId: UUID,
			action: String,
			metadata: Metadata?,
			ip: String?
		): AuditLog {
			return AuditLog(
				id = UUID.randomUUID(),
				userId = userId,
				action = action,
				metadata = metadata,
				ip = ip,
				createdAt = Instant.now(),
				updatedAt = Instant.now()
			)
		}
	}
}

@Serializable
data class Metadata (
	val data: String
)