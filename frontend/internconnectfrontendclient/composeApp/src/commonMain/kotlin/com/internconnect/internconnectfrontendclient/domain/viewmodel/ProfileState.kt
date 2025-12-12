package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.CompanyMemberJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.StudentJoined

sealed interface ProfileUiState {
	val loading: Boolean
	val error: String?
	val role: String?

	data class StudentState(
		override val loading: Boolean = true,
		override val error: String? = null,
		override val role: String? = "STUDENT",
		val data: StudentJoined? = null
	) : ProfileUiState

	data class CompanyMemberState(
		override val loading: Boolean = true,
		override val error: String? = null,
		override val role: String? = "COMPANY_MEMBER",
		val data: CompanyMemberJoined? = null
	) : ProfileUiState
}