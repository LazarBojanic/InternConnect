package com.internconnect.internconnectfrontendclient.di

import com.internconnect.internconnectfrontendclient.data.database.AppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getAppDatabase
import com.internconnect.internconnectfrontendclient.data.database.getDatabaseBuilder
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun platformEngine(): HttpClientEngineFactory<*> = Darwin

fun iosModule() = module {
	single { getAppDatabase(getDatabaseBuilder()) }
	single { get<AppDatabase>().getStudentProfileDao() }
	single { get<AppDatabase>().getCompanyMemberProfileDao() }
}

fun initKoinIos() {
	initKoin(
		baseUrl = "http://127.0.0.1:8800",
		iosModule()
	)
}