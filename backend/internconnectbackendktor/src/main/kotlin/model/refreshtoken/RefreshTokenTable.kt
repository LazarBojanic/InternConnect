package com.internconnect.model.refreshtoken

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object RefreshTokenTable : UUIDTable(name = "refresh_token") {
	val userID = reference("user_id", UserTable.id)
	val tokenHash = varchar("token_hash", length = 255)
	val issuedAt = datetime("issued_at").defaultExpression(CurrentDateTime)
	val expiresAt = datetime("expires_at").defaultExpression(CurrentDateTime)
	val revokedAt = datetime("revoked_at").defaultExpression(CurrentDateTime).nullable()
	val userAgent = varchar("user_agent", length = 255)
	val ip = varchar("ip", length = 255)
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
