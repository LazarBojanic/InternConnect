package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

class WelcomeViewModel(
	private val tokenStore: ITokenDataStore,
	private val api: IAppApi,
) {
	sealed interface State {
		data object Idle : State
		data object Checking : State
		data object NavigateHome : State
		data object StayOnWelcome : State
	}

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _state = MutableStateFlow<State>(State.Idle)
	val state: StateFlow<State> = _state

	fun checkAuth(userAgent: String? = null, ip: String? = null) {
		scope.launch {
			_state.value = State.Checking
			val current = tokenStore.tokenDto.value
			val access = current?.access
			val refresh = current?.refresh

			if (access.isNullOrBlank()) {
				_state.value = State.StayOnWelcome
				return@launch
			}

			if (!isJwtExpired(access)) {
				_state.value = State.NavigateHome
				return@launch
			}

			if (refresh.isNullOrBlank()) {
				_state.value = State.StayOnWelcome
				return@launch
			}
			val newToken = api.refreshToken()
			if (newToken != null) {
				tokenStore.setToken(newToken)
				_state.value = State.NavigateHome
			} else {
				tokenStore.clear()
				_state.value = State.StayOnWelcome
			}
		}
	}

	@OptIn(ExperimentalTime::class)
	private fun isJwtExpired(jwt: String): Boolean = try {
		/*val claims = jwtDecode(jwt)
		val expSec = claims["exp"]?.jsonPrimitive?.longOrNull ?: return true
		val nowSec = Clock.System.now().epochSeconds
		nowSec >= expSec*/
		false
	} catch (_: Throwable) { true }
}