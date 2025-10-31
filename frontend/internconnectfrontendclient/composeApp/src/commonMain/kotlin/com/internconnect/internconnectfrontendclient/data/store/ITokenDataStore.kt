package com.internconnect.internconnectfrontendclient.data.store

import com.internconnect.internconnectfrontendclient.data.dto.Token
import kotlinx.coroutines.flow.StateFlow

interface ITokenDataStore {
	val token: StateFlow<Token?>
	suspend fun setToken(token: Token)
	suspend fun clear()
}
