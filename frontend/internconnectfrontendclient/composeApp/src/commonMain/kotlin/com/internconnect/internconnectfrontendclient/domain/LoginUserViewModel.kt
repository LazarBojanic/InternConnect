package com.internconnect.internconnectfrontendclient.domain

import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.Token
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginUserViewModel(
	private val appApi: IAppApi
) {
	sealed interface UIState {
		object Idle : UIState
		object Loading : UIState
		data class LoggedIn(val token: Token) : UIState
		data class Error(val message: String) : UIState
	}

	private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
	val uiState: StateFlow<UIState> = _uiState

	private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

	fun login(dto: LoginUserDto) {
		_uiState.value = UIState.Loading
		scope.launch {
			try {
				val token = appApi.login(dto)
				if (token != null) {
					_uiState.value = UIState.LoggedIn(token)
				} else {
					_uiState.value = UIState.Error("Invalid credentials or server error")
				}
			} catch (t: Throwable) {
				_uiState.value = UIState.Error(t.message ?: "Login failed")
			}
		}
	}
}