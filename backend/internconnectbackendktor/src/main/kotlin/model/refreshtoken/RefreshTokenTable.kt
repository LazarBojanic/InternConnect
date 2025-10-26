package com.internconnect.model.refreshtoken

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object RefreshTokenTable : UUIDTable(name = "refresh_token") {
	val userId = reference("user_id", UserTable.id)
	val sessionId = uuid("session_id")
	val hash = varchar("hash", length = 255)
	val issuedAt = timestamp("issued_at")
	val expiresAt = timestamp("expires_at")
	val revokedAt = timestamp("revoked_at").nullable()
	val userAgent = varchar("user_agent", length = 255).nullable()
	val ip = varchar("ip", length = 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
