package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "company_member", foreignKeys = [ForeignKey(
	entity = User::class,
	parentColumns = ["id"],
	childColumns = ["userId"],
	onDelete = ForeignKey.CASCADE
)])
data class CompanyMember (
	@PrimaryKey(autoGenerate = false)
	val userId: String,
	@ColumnInfo(name = "company_id")
	val companyId: String,
	@ColumnInfo(name = "role")
	val role: String,
	@ColumnInfo(name = "status")
	val status: String,
	@ColumnInfo(name = "joined_at")
	val joinedAt: String?,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)
