package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	@ColumnInfo(name = "name")
	val name: String,
	@ColumnInfo(name = "industry")
	val industry: String,
	@ColumnInfo(name = "website")
	val website: String?,
	@ColumnInfo(name = "logoUrl")
	val logoUrl: String?,
	@ColumnInfo(name = "country")
	val country: String?,
	@ColumnInfo(name = "city")
	val city: String?,
	@ColumnInfo(name = "about")
	val about: String?,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)