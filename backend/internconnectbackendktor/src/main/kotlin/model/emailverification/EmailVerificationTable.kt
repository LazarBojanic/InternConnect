package com.internconnect.model.emailverification

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object EmailVerificationTable : UUIDTable(name = "email_verification") {
	val userId = reference("user_id", UserTable.id)
	val codeHash = varchar("code_hash", length = 255)
	val sentToEmail = varchar("sent_to_email", length = 10)
	val expiresAt = timestamp("expires_at")
	val consumedAt = timestamp("consumed_at").nullable().default(Instant.now())
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
