package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "internship")
data class Internship(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	@ColumnInfo(name = "company_id")
	val companyId: String,
	@ColumnInfo(name = "title")
	val title: String,
	@ColumnInfo(name = "category")
	val category: String,
	@ColumnInfo(name = "description")
	val description: String,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)