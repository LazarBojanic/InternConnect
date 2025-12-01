package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
	@PrimaryKey(autoGenerate = false)
	val id: String,
	@ColumnInfo(name = "email")
	val email: String,
	@ColumnInfo(name = "first_name")
	val firstName: String,
	@ColumnInfo(name = "last_name")
	val lastName: String,
	@ColumnInfo(name = "role")
	val role: String,
	@ColumnInfo(name = "is_email_verified")
	val isEmailVerified: Boolean,
	@ColumnInfo(name = "status")
	val status: String,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)