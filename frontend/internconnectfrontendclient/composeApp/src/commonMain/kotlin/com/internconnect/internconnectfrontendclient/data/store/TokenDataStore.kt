package com.internconnect.internconnectfrontendclient.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.internconnect.internconnectfrontendclient.data.dto.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TokenDataStore(private val dataStore: DataStore<Preferences>) : ITokenDataStore {

	private val keyAccess = stringPreferencesKey("token_access")
	private val keyRefresh = stringPreferencesKey("token_refresh")

	private val scope = CoroutineScope(Dispatchers.Default)

	private val tokenFlow: Flow<Token?> = dataStore.data.map { prefs ->
		val access = prefs[keyAccess]
		val refresh = prefs[keyRefresh]
		if (access != null || refresh != null) Token(access = access, refresh = refresh) else null
	}

	override val token = tokenFlow.stateIn(
		scope = scope,
		started = SharingStarted.Eagerly,
		initialValue = null
	)

	override suspend fun setToken(token: Token) {
		dataStore.edit { prefs ->
			if (!token.access.isNullOrBlank()) prefs[keyAccess] = token.access else prefs.remove(keyAccess)
			if (!token.refresh.isNullOrBlank()) prefs[keyRefresh] = token.refresh else prefs.remove(keyRefresh)
		}
	}

	override suspend fun clear() {
		dataStore.edit { prefs ->
			prefs.remove(keyAccess)
			prefs.remove(keyRefresh)
		}
	}
}