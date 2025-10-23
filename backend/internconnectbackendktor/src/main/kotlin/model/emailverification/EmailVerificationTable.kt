package com.internconnect.model.emailverification

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object EmailVerificationTable : UUIDTable(name = "email_verification") {
	val userID = reference("user_id", UserTable.id)
	val codeHash = varchar("code_hash", length = 255).nullable()
	val sentToEmail = varchar("sent_to_email", length = 10)
	val expiresAt = datetime("expires_at")
	val consumedAt = datetime("consumed_at").nullable()
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
