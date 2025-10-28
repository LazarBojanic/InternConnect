package com.internconnect.internconnectfrontendclient.data.store

import com.internconnect.internconnectfrontendclient.dto.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryTokenStore : ITokenStore {
	private val _token = MutableStateFlow<Token?>(null)
	override val token: StateFlow<Token?> = _token

	override suspend fun setToken(token: Token) {
		_token.value = token
	}

	override suspend fun clear() {
		_token.value = null
	}
}