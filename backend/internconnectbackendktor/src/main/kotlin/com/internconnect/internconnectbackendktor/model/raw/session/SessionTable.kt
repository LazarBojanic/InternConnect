package com.internconnect.internconnectbackendktor.model.raw.session

import com.internconnect.internconnectbackendktor.model.raw.user.UserRole
import com.internconnect.internconnectbackendktor.model.raw.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object SessionTable : UUIDTable(name = "session") {
	val userId = reference("user_id", UserTable.id)
	val ip = varchar("ip", length = 255).nullable()
	val userAgent = varchar("user_agent", length = 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}