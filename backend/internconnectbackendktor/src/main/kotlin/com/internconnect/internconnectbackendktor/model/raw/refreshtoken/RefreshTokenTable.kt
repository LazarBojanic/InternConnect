package com.internconnect.internconnectbackendktor.model.raw.refreshtoken

import com.internconnect.internconnectbackendktor.model.raw.session.SessionTable
import com.internconnect.internconnectbackendktor.model.raw.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object RefreshTokenTable : UUIDTable(name = "refresh_token") {
	val sessionId = reference("session_id", SessionTable.id)
	val hash = text("hash")
	val issuedAt = timestamp("issued_at").default(Instant.now())
	val expiresAt = timestamp("expires_at").default(Instant.now())
	val revokedAt = timestamp("revoked_at").default(Instant.now()).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
