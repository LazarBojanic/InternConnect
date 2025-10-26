package com.internconnect.model.studentprofile

import com.internconnect.model.student.StudentTable
import com.internconnect.model.user.UserTable
import com.internconnect.util.InstantSerializer
import com.internconnect.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.time.Instant
import java.util.UUID

class StudentEntity(userId: EntityID<UUID>) : UUIDEntity(userId) {
	companion object : UUIDEntityClass<StudentEntity>(StudentTable)
	var firstName by StudentTable.firstName
	var lastName by StudentTable.lastName
	var schoolName by StudentTable.schoolName
	var grade by StudentTable.grade
	var bio by StudentTable.bio
	var interests by StudentTable.interests
	var avatarUrl by StudentTable.avatarUrl
	var createdAt by StudentTable.createdAt
	var updatedAt by StudentTable.updatedAt
}

