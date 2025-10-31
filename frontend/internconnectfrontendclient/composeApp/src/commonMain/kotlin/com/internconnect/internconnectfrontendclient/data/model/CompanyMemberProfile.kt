package com.internconnect.internconnectfrontendclient.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_member_profile")
data class CompanyMemberProfile (
	@PrimaryKey(autoGenerate = false)
	val userId: String,
	@ColumnInfo(name = "user_email")
	val userEmail: String,
	@ColumnInfo(name = "user_full_name")
	val userFullName: String,
	@ColumnInfo(name = "user_role")
	val userRole: String,
	@ColumnInfo(name = "is_email_verified")
	val isEmailVerified: Boolean,
	@ColumnInfo(name = "user_status")
	val userStatus: String,
	@ColumnInfo(name = "joined_at")
	val joinedAt: String?,
	@ColumnInfo(name = "company_name")
	val companyName: String,
	@ColumnInfo(name = "company_industry")
	val companyIndustry: String,
	@ColumnInfo(name = "company_member_role")
	val companyMemberRole: String,
	@ColumnInfo(name = "company_member_status")
	val companyMemberStatus: String,
	@ColumnInfo(name = "website")
	val website: String?,
	@ColumnInfo(name = "logo_url")
	val logoUrl: String?,
	@ColumnInfo(name = "hq_country")
	val hqCountry: String?,
	@ColumnInfo(name = "city")
	val city: String?,
	@ColumnInfo(name = "about")
	val about: String?,
)
