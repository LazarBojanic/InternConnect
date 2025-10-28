package com.internconnect.internconnectfrontendclient.domain

import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyDto
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterCompanyViewModel(
	private val appApi: IAppApi
) {
	sealed interface UIState {
		object Idle : UIState
		object Loading : UIState
		data class Registered(val message: String?) : UIState
		data class Error(val message: String) : UIState
	}

	private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
	val uiState: StateFlow<UIState> = _uiState

	private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	fun register(dto: RegisterCompanyDto) {
		_uiState.value = UIState.Loading
		scope.launch {
			try {
				val msg = appApi.registerCompany(dto)
				_uiState.value = UIState.Registered(msg)
			} catch (t: Throwable) {
				_uiState.value = UIState.Error(t.message ?: "Registration failed")
			}
		}
	}
}