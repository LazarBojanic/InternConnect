package com.internconnect.model.auditlog

import com.internconnect.model.Metadata
import com.internconnect.model.user.UserTable
import com.internconnect.util.InetAddressSerializer
import com.internconnect.util.UUIDSerializer
import com.internconnect.util.Util
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime
import org.jetbrains.exposed.v1.json.jsonb
import java.net.InetAddress
import java.sql.Blob
import java.util.UUID

object AuditLogTable : UUIDTable(name = "audit_log") {
	val userID = reference("user_id", UserTable.id)
	val action = varchar("action", length = 255).nullable()
	val metadata = jsonb<Metadata>("metadata", Util.jsonFormat(), Metadata.serializer())
	//data type for sql?
	val ip = varchar("ip", length = 255)
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
