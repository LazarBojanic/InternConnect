package com.internconnect.internconnectfrontendclient.di

import android.content.Context
import com.internconnect.internconnectfrontendclient.data.database.AppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getAppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getDatabaseBuilder
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual fun platformEngine(): HttpClientEngineFactory<*> = OkHttp

fun androidModule(appContext: Context) = module {
	single { getAppDatabase(getDatabaseBuilder(appContext)) }
	single { get<AppDatabase>().getStudentProfileDao() }
	single { get<AppDatabase>().getCompanyMemberProfileDao() }
}