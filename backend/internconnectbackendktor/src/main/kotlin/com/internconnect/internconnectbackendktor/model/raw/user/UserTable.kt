package com.internconnect.internconnectbackendktor.model.raw.user

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object UserTable : UUIDTable(name = "user") {
	val email = varchar("email", length = 50).uniqueIndex()
	val firstName = varchar("first_name", length = 255)
	val lastName = varchar("last_name", length = 255)
	val role = enumeration("role", UserRole::class)
	val isEmailVerified = bool("is_email_verified").default(false)
	val status = enumeration("status", UserStatus::class)
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}