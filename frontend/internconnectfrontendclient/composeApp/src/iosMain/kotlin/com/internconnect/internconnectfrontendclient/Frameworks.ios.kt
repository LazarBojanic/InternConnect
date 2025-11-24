package com.internconnect.internconnectfrontendclient

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.internconnect.internconnectfrontendclient.data.database.AppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getAppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getDatabaseBuilder
import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.data.store.TokenDataStore
import com.internconnect.internconnectfrontendclient.data.store.provideTokenDataStore
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun platformEngine(): HttpClientEngineFactory<*> = Darwin

fun iosModule() = module {
	single { getAppDatabase(getDatabaseBuilder()) }
	single { get<AppDatabase>().getStudentProfileDao() }
	single { get<AppDatabase>().getCompanyMemberProfileDao() }
	single<DataStore<Preferences>> {
		provideTokenDataStore()
	}
	single<ITokenDataStore> { TokenDataStore(get()) }
}

fun initKoinIos() {
	initKoin(
		baseUrl = "https://srv1092316.hstgr.cloud",
		iosModule(),
	)
}
