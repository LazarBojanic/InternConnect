package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogoutViewModel(private val api: IAppApi, private val tokenStore: ITokenDataStore) {
	private val scope = CoroutineScope(Dispatchers.Default)

	fun logout(onDone: (() -> Unit)? = null) {
		scope.launch {
			try { api.logout() } catch (_: Throwable) { /* ignore */ }
			tokenStore.clear()
			onDone?.invoke()
		}
	}
}