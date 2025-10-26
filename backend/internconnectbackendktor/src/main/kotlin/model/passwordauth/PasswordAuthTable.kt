package com.internconnect.model.passwordauth

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object PasswordAuthTable : UUIDTable(name = "password_auth") {
	val userId = reference("user_id", UserTable.id)
	val encryptedPassword = varchar("encrypted_password", length = 255)
	val encryptionAlgorithm = varchar("encryption_algorithm", length = 20)
	val passwordSetAt = timestamp("password_set_at")
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}


