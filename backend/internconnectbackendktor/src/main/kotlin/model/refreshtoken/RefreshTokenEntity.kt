package com.internconnect.model.refreshtoken

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class RefreshTokenEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<RefreshTokenEntity>(RefreshTokenTable)

	var userId by RefreshTokenTable.userId
	var sessionId by RefreshTokenTable.sessionId
	var hash by RefreshTokenTable.hash
	var issuedAt by RefreshTokenTable.issuedAt
	var expiresAt by RefreshTokenTable.expiresAt
	var revokedAt by RefreshTokenTable.revokedAt
	var userAgent by RefreshTokenTable.userAgent
	var ip by RefreshTokenTable.ip
	var createdAt by RefreshTokenTable.createdAt
	var updatedAt by RefreshTokenTable.updatedAt
}
