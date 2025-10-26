package com.internconnect.model.student

import com.internconnect.model.user.UserTable
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant
import java.util.UUID

object StudentTable : UUIDTable(name = "student") {
	val userID = reference("user_id", UserTable.id)
	val firstName = varchar("first_name", length = 255)
	val lastName = varchar("last_name", length = 255)
	val schoolName = varchar("school_name", length = 255)
	val grade = integer("grade")
	val bio = varchar("bio", length = 255).nullable()
	val interests = text("interests").nullable()
	val avatarUrl = varchar("avatar_url", length = 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
