package com.internconnect.internconnectfrontendclient

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.internconnect.internconnectfrontendclient.data.database.AppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getAppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getDatabaseBuilder
import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.data.store.TokenDataStore
import com.internconnect.internconnectfrontendclient.data.store.provideTokenDataStore
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual fun platformEngine(): HttpClientEngineFactory<*> = OkHttp


fun androidModule(appContext: Context) = module {
	single { getAppDatabase(getDatabaseBuilder(appContext)) }
	single<DataStore<Preferences>> {
		provideTokenDataStore(appContext)
	}
	single<ITokenDataStore> { TokenDataStore(get()) }
	single { get<AppDatabase>().getUserDao() }
	single { get<AppDatabase>().getStudentDao() }
	single { get<AppDatabase>().getCompanyMemberDao() }
	single { get<AppDatabase>().getCompanyDao() }
	single { get<AppDatabase>().getInternshipDao() }
	single { get<AppDatabase>().getInternshipApplicationDao() }


}
