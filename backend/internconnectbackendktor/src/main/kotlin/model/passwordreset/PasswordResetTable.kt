package com.internconnect.model.passwordreset

import com.internconnect.model.organization.OrganizationTable
import com.internconnect.model.user.UserTable
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime
import java.util.UUID

object PasswordResetTable : UUIDTable(name = "password_reset") {
	val userID = reference("user_id", UserTable.id)
	val codeHash = varchar("code_hash", length = 255)
	val expiresAt = datetime("expires_at").defaultExpression(CurrentDateTime)
	val consumedAt = datetime("consumed_at").defaultExpression(CurrentDateTime).nullable()
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}

