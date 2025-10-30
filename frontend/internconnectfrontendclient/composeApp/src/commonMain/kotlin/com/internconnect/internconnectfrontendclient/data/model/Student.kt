package com.internconnect.internconnectfrontendclient.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student (
	@PrimaryKey(autoGenerate = false)
	val userId: String,
	@ColumnInfo(name = "email")
	val email: String,
	@ColumnInfo(name = "firstName")
	val firstName: String,
	@ColumnInfo(name = "schoolName")
	val lastName: String,
	@ColumnInfo(name = "fullName")
	val fullName: String,
	@ColumnInfo(name = "userRole")
	val userRole: String,
	@ColumnInfo(name = "isEmailVerified")
	val isEmailVerified: String,
	@ColumnInfo(name = "userStatus")
	val userStatus: String,
	@ColumnInfo(name = "grade")
	val grade: Int,
	@ColumnInfo(name = "bio")
	val bio: String?,
	@ColumnInfo(name = "interests")
	val interests: String?,
	@ColumnInfo(name = "avatarUrl")
	val avatarUrl: String?
)
