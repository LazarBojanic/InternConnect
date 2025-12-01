package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "student", foreignKeys = [ForeignKey(
	entity = User::class,
	parentColumns = ["id"],
	childColumns = ["userId"],
	onDelete = ForeignKey.CASCADE
)])
data class Student (
	@PrimaryKey(autoGenerate = false)
	val userId: String,
	@ColumnInfo(name = "school_name")
	val schoolName: String,
	@ColumnInfo(name = "grade")
	val grade: Int,
	@ColumnInfo(name = "bio")
	val bio: String?,
	@ColumnInfo(name = "interests")
	val interests: String?,
	@ColumnInfo(name = "avatarUrl")
	val avatarUrl: String?,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)
