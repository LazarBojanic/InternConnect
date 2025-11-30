package com.internconnect.internconnectbackendktor.model.raw.auditlog

import com.internconnect.internconnectbackendktor.model.raw.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object AuditLogTable : UUIDTable(name = "audit_log") {
	val userId = reference("user_id", UserTable.id)
	val action = varchar("action", length = 255)
	val metadata = blob("metadata").nullable()
	val ip = varchar("ip", length = 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
