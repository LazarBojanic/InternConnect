package com.internconnect.dto

import kotlinx.serialization.Serializable

@Serializable
data class CompanyMemberProfileDto(
	val userId: String,
	val userEmail: String,
	val userFullName: String,
	val userRole: String,
	val isEmailVerified: Boolean,
	val userStatus: String,
	val companyName: String,
	val companyIndustry: String,
	val companyMemberRole: String,
	val companyMemberStatus: String,
	val joinedAt: String?,
	val website: String?,
	val logoUrl: String?,
	val hqCountry: String?,
	val city: String?,
	val about: String?,
)