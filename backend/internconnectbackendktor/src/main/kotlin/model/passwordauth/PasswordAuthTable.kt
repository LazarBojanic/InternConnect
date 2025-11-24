package com.internconnect.model.passwordauth

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant
import java.util.*


object PasswordAuthTable : IdTable<UUID>(name = "password_auth") {
	override val id: Column<EntityID<UUID>> =
		reference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE).uniqueIndex()
	val userId = id
	val encryptedPassword = varchar("encrypted_password", 255)
	val encryptionAlgorithm = varchar("encryption_algorithm", 20)
	val passwordSetAt = timestamp("password_set_at")
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())

}


