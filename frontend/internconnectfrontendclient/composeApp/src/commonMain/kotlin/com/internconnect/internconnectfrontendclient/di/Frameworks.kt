package com.internconnect.internconnectfrontendclient.di

import com.internconnect.internconnectfrontendclient.data.store.ITokenStore
import com.internconnect.internconnectfrontendclient.data.store.InMemoryTokenStore
import com.internconnect.internconnectfrontendclient.domain.LoginUserViewModel
import com.internconnect.internconnectfrontendclient.domain.RegisterCompanyViewModel
import com.internconnect.internconnectfrontendclient.domain.RegisterStudentViewModel
import com.internconnect.internconnectfrontendclient.http.AppApi
import com.internconnect.internconnectfrontendclient.http.AppHttpClient
import com.internconnect.internconnectfrontendclient.http.IAppApi
import io.ktor.client.engine.*
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect fun platformEngine(): HttpClientEngineFactory<*>

fun commonModule(baseUrl: String) = module {
	single<ITokenStore> { InMemoryTokenStore() }
	single { AppHttpClient(baseUrl, platformEngine(), get()) }
	single<IAppApi> { AppApi(get()) }
	single { LoginUserViewModel(get(), get()) }
	single { RegisterStudentViewModel(get()) }
	single { RegisterCompanyViewModel(get()) }
}

fun initKoin(baseUrl: String) {
	startKoin {
		modules(commonModule(baseUrl))
	}
}