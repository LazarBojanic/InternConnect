package com.internconnect.model.auditlog

import com.internconnect.model.Metadata
import com.internconnect.util.InetAddressSerializer
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.net.InetAddress
import java.time.Instant
import java.util.UUID

@Serializable
data class AuditLog(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val action: String,
	val metadata: Metadata,
	@Serializable(with = InetAddressSerializer::class)
	val ip: InetAddress,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)