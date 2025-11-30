package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.domain.repository.IUserRepository
import com.internconnect.internconnectfrontendclient.domain.util.jwtDecode
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
	private val userRepository: IUserRepository,
	private val api: IAppApi,
	private val tokenStore: ITokenDataStore,
) {
	private val scope = CoroutineScope(Dispatchers.Default)

	private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState.StudentState(loading = true))
	val uiState: StateFlow<ProfileUiState> = _uiState

	fun load() {
		scope.launch {
			try {
				val cachedStudentProfile = userRepository.getCurrentStudentProfile()
				val cachedCompanyMemberProfile = userRepository.getCurrentCompanyMemberProfile()

				if (cachedStudentProfile != null) {
					_uiState.value = ProfileUiState.StudentState(loading = false, data = cachedStudentProfile)
					return@launch
				}
				if (cachedCompanyMemberProfile != null) {
					_uiState.value = ProfileUiState.CompanyMemberState(loading = false, data = cachedCompanyMemberProfile)
					return@launch
				}

				val access: String? = tokenStore.tokenDto.value?.access
				var userId: String? = null
				var userRole: String? = null
				if(access !=  null) {
					userId = jwtDecode(access)["userId"]?.toString()
					if (userId != null) {
						userRole = jwtDecode(access)["userRole"]?.toString()
						if(userRole == "STUDENT") {
							val studentProfileDto = api.fetchStudentProfileById(userId)
							if (studentProfileDto != null) {
								userRepository.setCurrentStudentProfile(studentProfileDto)
								_uiState.value = ProfileUiState.StudentState(loading = false, data = studentProfileDto)
								return@launch
							}
						}
						else if(userRole == "COMPANY_MEMBER") {
							val companyMemberProfileDto = api.fetchCompanyMemberProfileById(userId)
							if (companyMemberProfileDto != null) {
								userRepository.setCurrentCompanyMemberProfile(companyMemberProfileDto)
								_uiState.value = ProfileUiState.CompanyMemberState(loading = false, data = companyMemberProfileDto)
								return@launch
							}
						}
					}
				}
			}
			catch (t: Throwable) {
				_uiState.value = _uiState.value.let {
					when (it) {
						is ProfileUiState.StudentState -> it.copy(loading = false, error = t.message ?: "Unknown error")
						is ProfileUiState.CompanyMemberState -> it.copy(loading = false, error = t.message ?: "Unknown error")
					}
				}
			}
		}
	}
}