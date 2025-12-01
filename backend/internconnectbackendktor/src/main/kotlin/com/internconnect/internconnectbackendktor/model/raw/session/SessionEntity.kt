package com.internconnect.internconnectbackendktor.model.raw.session

import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.time.Instant
import java.util.UUID

class SessionEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<SessionEntity>(SessionTable)
	var userId by SessionTable.userId
	var ip by SessionTable.ip
	var userAgent by SessionTable.userAgent
	var createdAt by SessionTable.createdAt
	var updatedAt by SessionTable.updatedAt
}