package com.internconnect.internconnectbackendktor.model.joined

import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberRole
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberStatus
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CompanyMemberJoined(
	val user: UserJoined,
	val company: CompanyJoined,
	val role: CompanyMemberRole,
	val status: CompanyMemberStatus,
	@Serializable(with = InstantSerializer::class)
	val joinedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)