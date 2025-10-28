package com.internconnect.internconnectfrontendclient.data.store

import com.internconnect.internconnectfrontendclient.dto.Token
import kotlinx.coroutines.flow.StateFlow

interface ITokenStore {
	val token: StateFlow<Token?>
	suspend fun setToken(token: Token)
	suspend fun clear()
}
