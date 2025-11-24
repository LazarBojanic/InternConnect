package com.internconnect.model.auditlog

import com.internconnect.model.user.UserTable
import com.internconnect.util.Util
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import org.jetbrains.exposed.v1.json.jsonb
import java.time.Instant

object AuditLogTable : UUIDTable(name = "audit_log") {
	val userId = reference("user_id", UserTable.id)
	val action = varchar("action", length = 255)
	val metadata = jsonb<Metadata>("metadata", Util.jsonFormat(), Metadata.serializer()).nullable()
	val ip = varchar("ip", length = 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
