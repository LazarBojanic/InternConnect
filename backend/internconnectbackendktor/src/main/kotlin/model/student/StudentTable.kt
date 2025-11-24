package com.internconnect.model.student

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant
import java.util.*


object StudentTable : IdTable<UUID>(name = "student") {
	override val id: Column<EntityID<UUID>> =
		reference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE).uniqueIndex()
	val userId = id
	val firstName = varchar("first_name", 255)
	val lastName = varchar("last_name", 255)
	val schoolName = varchar("school_name", 255)
	val grade = integer("grade")
	val bio = varchar("bio", 255).nullable()
	val interests = text("interests").nullable()
	val avatarUrl = varchar("avatar_url", 255).nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())

}