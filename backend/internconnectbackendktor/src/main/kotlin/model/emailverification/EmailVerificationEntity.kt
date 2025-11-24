package com.internconnect.model.emailverification

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class EmailVerificationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<EmailVerificationEntity>(EmailVerificationTable)

	var userId by EmailVerificationTable.userId
	var codeHash by EmailVerificationTable.codeHash
	var sentToEmail by EmailVerificationTable.sentToEmail
	var expiresAt by EmailVerificationTable.expiresAt
	var consumedAt by EmailVerificationTable.consumedAt
	var createdAt by EmailVerificationTable.createdAt
	var updatedAt by EmailVerificationTable.updatedAt
}


