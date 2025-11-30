package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.dto.request.RegisterCompanyMemberDto
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterCompanyMemberViewModel(
	private val api: IAppApi,
) {
	sealed interface UiState {
		data object Idle : UiState
		data object Loading : UiState
		data class Error(val message: String) : UiState
		data object Success : UiState
	}

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
	val uiState: StateFlow<UiState> = _uiState

	fun register(registerCompanyMemberDto: RegisterCompanyMemberDto) {
		scope.launch {
			_uiState.value = UiState.Loading
			try {
				val result: String? = api.registerCompanyMember(registerCompanyMemberDto)
				_uiState.value = UiState.Success
			} catch (t: Throwable) {
				_uiState.value = UiState.Error(t.message ?: "Registration failed")
			}
		}
	}
}