package com.internconnect.model.user


import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<UserEntity>(UserTable)
	var email by UserTable.email
	var fullName by UserTable.fullName
	var role by UserTable.role
	var isEmailVerified by UserTable.isEmailVerified
	var status by UserTable.status
	var createdAt by UserTable.createdAt
	var updatedAt by UserTable.updatedAt
}