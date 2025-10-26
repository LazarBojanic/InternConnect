package com.internconnect.model.companymember

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID
@Serializable
//TODO composite PK
data class CompanyMember(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyID: UUID,
	@Serializable(with = UUIDSerializer::class)
	val userID: UUID,
	val companyMemberRole: CompanyMemberRole,
	val companyMemberStatus: CompanyMemberStatus,
	@Serializable(with = InstantSerializer::class)
	val joinedAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
)

enum class CompanyMemberRole {
	owner, admin, recruiter, viewer
}
enum class CompanyMemberStatus {
	invited, active, disabled
}