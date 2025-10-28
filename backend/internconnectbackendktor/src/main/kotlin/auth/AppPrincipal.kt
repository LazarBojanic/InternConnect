package com.internconnect.auth

import com.internconnect.model.user.UserRole
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.security.Principal
import java.util.UUID

@Serializable
data class AppPrincipal(
	@Serializable(with = UUIDSerializer::class)
	val userId: UUID,
	val role: UserRole,
	@Serializable(with = UUIDSerializer::class)
	val orgId: UUID? = null,
	val orgRoles: Set<String> = emptySet()
) : Principal {
	override fun getName(): String? {
		return userId.toString()
	}
}