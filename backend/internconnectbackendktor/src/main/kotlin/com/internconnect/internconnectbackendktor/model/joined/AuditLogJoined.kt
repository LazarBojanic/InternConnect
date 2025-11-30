package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import java.time.Instant
import java.util.UUID

@Serializable
data class AuditLogJoined(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val user: UserJoined,
	val action: String,
	@Serializable(with = ExposedBlobSerializer::class)
	val metadata: ExposedBlob?,
	val ip: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)