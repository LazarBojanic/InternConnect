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
	single<DataStore<Preferences>> {
		provideTokenDataStore()
	}
	single<ITokenDataStore> { TokenDataStore(get()) }
	single { get<AppDatabase>().getUserDao() }
	single { get<AppDatabase>().getStudentDao() }
	single { get<AppDatabase>().getCompanyMemberDao() }
	single { get<AppDatabase>().getCompanyDao() }
	single { get<AppDatabase>().getInternshipDao() }
	single { get<AppDatabase>().getInternshipApplicationDao() }

}

fun initKoinIos() {
	val dev = false
	var url = ""
	if(dev){
		url = "http://127.0.0.1:8080"
	}
	else{
		url = "https://srv1092316.hstgr.cloud"
	}
	initKoin(
		baseUrl = url,
		iosModule(),
	)
}
