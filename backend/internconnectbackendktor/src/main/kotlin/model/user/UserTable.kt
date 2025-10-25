package com.internconnect.model.user

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object UserTable : UUIDTable(name = "user") {
	val email = varchar("name", length = 50).uniqueIndex()
	val fullName = varchar("full_name", length = 255).nullable()
	val role = varchar("role", length = 10)
	val isEmailVerified = bool("is_email_verified").default(false)
	val status = varchar("status", length = 20).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}