package com.internconnect.internconnectbackendktor.model.raw.companymember

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class CompanyMember(
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyId: UUID,
	val role: CompanyMemberRole,
	val status: CompanyMemberStatus,
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
			role: CompanyMemberRole,
			status: CompanyMemberStatus,
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
				role = role,
				status = status,
				joinedAt = joinedAt,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}

enum class CompanyMemberRole {
	OWNER, ADMIN, RECRUITER, VIEWER
}

enum class CompanyMemberStatus {
	INVITED, ACTIVE, SUSPENDED, DELETED
}