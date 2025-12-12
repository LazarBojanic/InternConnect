package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.domain.util.jwtDecode
import com.internconnect.internconnectfrontendclient.data.model.dto.TokenDto
import com.internconnect.internconnectfrontendclient.data.model.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.toRaw
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyMemberRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IStudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IUserRepository
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.jsonPrimitive


class LoginUserViewModel(
	private val api: IAppApi,
	private val tokenStore: ITokenDataStore,
	private val userRepository: IUserRepository,
	private val studentRepository: IStudentRepository,
	private val companyRepository: ICompanyRepository,
	private val companyMemberRepository: ICompanyMemberRepository,
) {
	sealed interface LoginUiState {
		data object Idle : LoginUiState
		data object Loading : LoginUiState
		data class Error(val message: String) : LoginUiState
		data class LoggedInStudent(val userId: String) : LoginUiState
		data class LoggedInCompanyMember(val userId: String) : LoginUiState
	}

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
	val uiState: StateFlow<LoginUiState> = _uiState

	fun login(dto: LoginUserDto) {
		scope.launch {
			_uiState.value = LoginUiState.Loading
			try {
				val token = api.login(dto)
				if(token != null) {
					tokenStore.setToken(TokenDto(token.access, token.refresh))
					val access = token.access
					if(access != null) {
						val claims = jwtDecode(access)
						val userId = claims["sub"]?.jsonPrimitive?.content
						val role = claims["role"]?.jsonPrimitive?.content
						if(userId != null && role != null) {
							when (role) {
								"STUDENT" -> {
									val studentDto = api.fetchStudentById(userId)
									if(studentDto != null){
										userRepository.setCurrentUser(studentDto.user.toRaw())
										studentRepository.setCurrentStudent(studentDto.toRaw())
									}
									_uiState.value = LoginUiState.LoggedInStudent(userId)
								}
								"COMPANY_MEMBER" -> {
									val companyMemberDto = api.fetchCompanyMemberById(userId)
									if(companyMemberDto != null){
										userRepository.setCurrentUser(companyMemberDto.user.toRaw())
										companyRepository.setCurrentCompany(companyMemberDto.company.toRaw())
										companyMemberRepository.setCurrentCompanyMember(companyMemberDto.toRaw())
									}
									_uiState.value = LoginUiState.LoggedInCompanyMember(userId)
								}
								else -> _uiState.value = LoginUiState.Error("Unsupported role: $role")
							}
						}
						else {
							_uiState.value = LoginUiState.Error("Missing userId or role claims")
						}
					}
					else {
						_uiState.value = LoginUiState.Error("Missing access token")
					}
				}
				else {
					_uiState.value = LoginUiState.Error("Login failed")
				}
			} catch (t: Throwable) {
				_uiState.value = LoginUiState.Error(t.message ?: "Login failed")
			}
		}
	}
}