package com.internconnect.model.passwordauth

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class PasswordAuthEntity(userId: EntityID<UUID>) : UUIDEntity(userId) {
	companion object : UUIDEntityClass<PasswordAuthEntity>(PasswordAuthTable)

	var encryptedPassword by PasswordAuthTable.encryptedPassword
	var encryptionAlgorithm by PasswordAuthTable.encryptionAlgorithm
	var passwordSetAt by PasswordAuthTable.passwordSetAt
	var createdAt by PasswordAuthTable.createdAt
	var updatedAt by PasswordAuthTable.updatedAt

}
