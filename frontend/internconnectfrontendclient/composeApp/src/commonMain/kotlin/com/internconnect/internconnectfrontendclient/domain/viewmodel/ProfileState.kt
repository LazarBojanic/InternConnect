package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto

sealed interface ProfileUiState {
	val loading: Boolean
	val error: String?
	val role: String?

	data class StudentState(
		override val loading: Boolean = true,
		override val error: String? = null,
		override val role: String? = "STUDENT",
		val data: StudentProfileDto? = null
	) : ProfileUiState

	data class CompanyMemberState(
		override val loading: Boolean = true,
		override val error: String? = null,
		override val role: String? = "COMPANY_MEMBER",
		val data: CompanyMemberProfileDto? = null
	) : ProfileUiState
}