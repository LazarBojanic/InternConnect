package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.domain.repository.IUserRepository
import com.internconnect.internconnectfrontendclient.domain.util.jwtDecode
import com.internconnect.internconnectfrontendclient.data.dto.request.LoginUserDto
import com.internconnect.internconnectfrontendclient.data.dto.TokenDto
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
						val userRole = claims["userRole"]?.jsonPrimitive?.content
						if(userId != null && userRole != null) {
							when (userRole) {
								"STUDENT" -> {
									api.fetchStudentProfileById(userId)?.let { userRepository.setCurrentStudentProfile(it) }
									_uiState.value = LoginUiState.LoggedInStudent(userId)
								}
								"COMPANY_MEMBER" -> {
									api.fetchCompanyMemberProfileById(userId)?.let { userRepository.setCurrentCompanyMemberProfile(it) }
									_uiState.value = LoginUiState.LoggedInCompanyMember(userId)
								}
								else -> _uiState.value = LoginUiState.Error("Unsupported role: $userRole")
							}
						}
						else {
							_uiState.value = LoginUiState.Error("Missing userId or userRole claims")
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