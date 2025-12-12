package com.internconnect.internconnectbackendktor.model.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CompanyMemberDto(
	val user: UserDto,
	val company: CompanyDto,
	val role: String,
	val status: String,
	val joinedAt: String?,
	val createdAt: String,
	val updatedAt: String
)