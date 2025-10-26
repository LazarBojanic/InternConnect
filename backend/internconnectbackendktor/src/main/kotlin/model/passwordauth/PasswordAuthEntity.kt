package com.internconnect.model.passwordauth

import com.internconnect.model.student.StudentTable
import com.internconnect.util.InstantSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.time.Instant
import java.util.UUID

class PasswordAuthEntity(userId: EntityID<UUID>) : UUIDEntity(userId) {
	companion object : UUIDEntityClass<PasswordAuthEntity>(PasswordAuthTable)
	var encryptedPassword by PasswordAuthTable.encryptedPassword
	var encryptionAlgorithm by PasswordAuthTable.encryptionAlgorithm
	var passwordSetAt by PasswordAuthTable.passwordSetAt
	val createdAt by PasswordAuthTable.createdAt
	val updatedAt by PasswordAuthTable.updatedAt

}
