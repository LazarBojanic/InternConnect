package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.join
import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.data.toRaw
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.CompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.StudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyMemberRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IStudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IUserRepository
import com.internconnect.internconnectfrontendclient.domain.util.jwtDecode
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
	private val api: IAppApi,
	private val tokenStore: ITokenDataStore,
	private val userRepository: IUserRepository,
	private val studentRepository: IStudentRepository,
	private val companyRepository: ICompanyRepository,
	private val companyMemberRepository: ICompanyMemberRepository,
) {
	private val scope = CoroutineScope(Dispatchers.Default)

	private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState.StudentState(loading = true))
	val uiState: StateFlow<ProfileUiState> = _uiState

	fun load() {
		scope.launch {
			try {
				val cachedUser = userRepository.getCurrentUser()
				val cachedStudent = studentRepository.getCurrentStudent()
				val cachedCompany = companyRepository.getCurrentCompany()
				val cachedCompanyMember = companyMemberRepository.getCurrentCompanyMember()

				if(cachedUser != null && cachedCompany != null) {
					if(cachedStudent != null){
						_uiState.value = ProfileUiState.StudentState(loading = false, data = cachedStudent.join(cachedUser.join()))
						return@launch
					}
					else if (cachedCompanyMember != null) {
						_uiState.value = ProfileUiState.CompanyMemberState(loading = false, data = cachedCompanyMember.join(cachedUser.join(), cachedCompany.join()))
						return@launch
					}
				}

				val access: String? = tokenStore.tokenDto.value?.access
				var userId: String? = null
				var userRole: String? = null
				if(access !=  null) {
					userId = jwtDecode(access)["userId"]?.toString()
					userRole = jwtDecode(access)["userRole"]?.toString()
					if (userId != null) {
						if(userRole == "STUDENT") {
							val studentDto = api.fetchStudentById(userId)
							if (studentDto != null) {
								studentRepository.setCurrentStudent(studentDto.toRaw())
								_uiState.value = ProfileUiState.StudentState(loading = false, data = studentDto.toJoined())
								return@launch
							}
						}
						else if(userRole == "COMPANY_MEMBER") {
							val companyMemberDto = api.fetchCompanyMemberById(userId)
							if (companyMemberDto != null) {
								companyMemberRepository.setCurrentCompanyMember(companyMemberDto.toRaw())
								_uiState.value = ProfileUiState.CompanyMemberState(loading = false, data = companyMemberDto.toJoined())
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