package com.internconnect.model.passwordreset

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*


class PasswordResetEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<PasswordResetEntity>(PasswordResetTable)
	var userId by PasswordResetTable.userId
	var codeHash by PasswordResetTable.codeHash
	var expiresAt by PasswordResetTable.expiresAt
	var consumedAt by PasswordResetTable.consumedAt
	var createdAt by PasswordResetTable.createdAt
	var updatedAt by PasswordResetTable.updatedAt
}
