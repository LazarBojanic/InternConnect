package com.internconnect.model.passwordreset

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object PasswordResetTable : UUIDTable(name = "password_reset") {
	val userID = reference("user_id", UserTable.id)
	val codeHash = varchar("code_hash", length = 255)
	val expiresAt = timestamp("expires_at")
	val consumedAt = timestamp("consumed_at").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}

