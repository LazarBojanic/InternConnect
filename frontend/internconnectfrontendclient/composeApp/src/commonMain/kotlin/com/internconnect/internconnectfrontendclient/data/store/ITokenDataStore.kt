package com.internconnect.internconnectfrontendclient.data.store

import com.internconnect.internconnectfrontendclient.data.dto.TokenDto
import kotlinx.coroutines.flow.StateFlow

interface ITokenDataStore {
	val tokenDto: StateFlow<TokenDto?>
	suspend fun setToken(tokenDto: TokenDto)
	suspend fun clear()
}
