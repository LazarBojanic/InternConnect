package com.internconnect.internconnectfrontendclient

import com.internconnect.internconnectfrontendclient.domain.repository.IUserRepository
import com.internconnect.internconnectfrontendclient.domain.repository.UserRepository
import com.internconnect.internconnectfrontendclient.domain.viewmodel.LoginUserViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.RegisterCompanyMemberViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.RegisterStudentViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.WelcomeViewModel
import com.internconnect.internconnectfrontendclient.http.AppApi
import com.internconnect.internconnectfrontendclient.http.AppHttpClient
import com.internconnect.internconnectfrontendclient.http.IAppApi
import io.ktor.client.engine.*
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformEngine(): HttpClientEngineFactory<*>

fun commonModule(baseUrl: String) = module {
	// network
	single { AppHttpClient(baseUrl, platformEngine(), get()) }
	single<IAppApi> { AppApi(get(), get()) }

	// repositories
	single<IUserRepository> { UserRepository(get(), get()) }

	// view models
	factory { LoginUserViewModel(get(), get(), get()) }
	factory { RegisterStudentViewModel(get()) }
	factory { RegisterCompanyMemberViewModel(get()) }
	factory { ProfileViewModel(get(), get(), get()) }
	factory { WelcomeViewModel(get(), get()) }
}
fun initKoin(baseUrl: String, vararg platformModules: Module) {
	startKoin {
		modules(listOf(commonModule(baseUrl)) + platformModules)
	}
}

