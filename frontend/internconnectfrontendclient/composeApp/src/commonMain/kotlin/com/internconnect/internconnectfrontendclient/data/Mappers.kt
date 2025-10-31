package com.internconnect.internconnectfrontendclient.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto
import com.internconnect.internconnectfrontendclient.data.model.CompanyMemberProfile
import com.internconnect.internconnectfrontendclient.data.model.StudentProfile
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun StudentProfileDto.toDomain(): StudentProfile{
	return StudentProfile(
		userId = this.userId,
		email = this.email,
		firstName = this.firstName,
		lastName = this.lastName,
		fullName = this.fullName,
		userRole = this.userRole,
		isEmailVerified = this.isEmailVerified,
		userStatus = this.userStatus,
		schoolName = this.schoolName,
		grade = this.grade,
		bio = this.bio,
		interests = this.interests,
		avatarUrl = this.avatarUrl
	)
}
@OptIn(ExperimentalTime::class)
fun CompanyMemberProfileDto.toDomain(): CompanyMemberProfile{
	return CompanyMemberProfile(
		userId = this.userId,
		userEmail = this.userEmail,
		userFullName = this.userFullName,
		userRole = this.userRole,
		isEmailVerified = this.isEmailVerified,
		userStatus = this.userStatus,
		companyName = this.companyName,
		companyIndustry = this.companyIndustry,
		companyMemberRole = this.companyMemberRole,
		companyMemberStatus = this.companyMemberStatus,
		joinedAt = this.joinedAt,
		website = this.website,
		logoUrl = this.logoUrl,
		hqCountry = this.hqCountry,
		city = this.city,
		about = this.about
	)
}

fun StudentProfile.toDto(): StudentProfileDto = StudentProfileDto(
	userId = userId,
	email = email,
	firstName = firstName,
	lastName = lastName,
	fullName = fullName,
	userRole = userRole,
	isEmailVerified = isEmailVerified,
	userStatus = userStatus,
	schoolName = schoolName,
	grade = grade,
	bio = bio,
	interests = interests,
	avatarUrl = avatarUrl,
)

fun CompanyMemberProfile.toDto(): CompanyMemberProfileDto = CompanyMemberProfileDto(
	userId = userId,
	userEmail = userEmail,
	userFullName = userFullName,
	userRole = userRole,
	isEmailVerified = isEmailVerified,
	userStatus = userStatus,
	companyName = companyName,
	companyIndustry = companyIndustry,
	companyMemberRole = companyMemberRole,
	companyMemberStatus = companyMemberStatus,
	joinedAt = joinedAt,
	website = website,
	logoUrl = logoUrl,
	hqCountry = hqCountry,
	city = city,
	about = about,
)