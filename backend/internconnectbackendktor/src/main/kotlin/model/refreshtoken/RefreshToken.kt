package com.internconnect.model.refreshtoken

import com.internconnect.util.InetAddressSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.net.InetAddress
import java.util.UUID
@Serializable
data class RefreshToken(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val tokenHash: String,
	val issuedAt: LocalDateTime,
	val expiresAt: LocalDateTime,
	val revokedAt: LocalDateTime?,
	val userAgent: String,
	@Serializable(with = InetAddressSerializer::class)
	val ip: InetAddress,
	val createdAt: LocalDateTime,
	val updatedAt: LocalDateTime,
)