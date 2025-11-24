package com.internconnect.model.companymember

import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class CompanyMember(
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyId: UUID,
	val companyMemberRole: CompanyMemberRole,
	val companyMemberStatus: CompanyMemberStatus,
	@Serializable(with = InstantSerializer::class)
	val joinedAt: Instant?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
) {
	companion object {
		fun createNew(
			companyId: UUID,
			userId: UUID,
			companyMemberRole: CompanyMemberRole,
			companyMemberStatus: CompanyMemberStatus,
			joinedAt: Instant? = null,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): CompanyMember {
			val now = Instant.now()
			val joinedAt = joinedAt ?: now
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return CompanyMember(
				companyId = companyId,
				userId = userId,
				companyMemberRole = companyMemberRole,
				companyMemberStatus = companyMemberStatus,
				joinedAt = joinedAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}

enum class CompanyMemberRole {
	owner, admin, recruiter, viewer
}

enum class CompanyMemberStatus {
	invited, active, disabled
}