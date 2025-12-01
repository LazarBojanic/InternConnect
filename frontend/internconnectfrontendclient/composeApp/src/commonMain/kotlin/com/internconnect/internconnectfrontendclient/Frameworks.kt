package com.internconnect.internconnectfrontendclient

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.data.store.TokenDataStore
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.CompanyMemberRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.CompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.InternshipApplicationRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.InternshipRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.StudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.UserRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyMemberRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IInternshipApplicationRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IInternshipRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IStudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IUserRepository
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
	single<IUserRepository> { UserRepository(get()) }
	single<IStudentRepository> { StudentRepository(get()) }
	single<ICompanyMemberRepository> { CompanyMemberRepository(get()) }
	single<ICompanyRepository> { CompanyRepository(get()) }
	single<IInternshipRepository> { InternshipRepository(get()) }
	single<IInternshipApplicationRepository> { InternshipApplicationRepository(get()) }

	// view models
	factory { LoginUserViewModel(get(), get(), get(), get(), get()) }
	factory { RegisterStudentViewModel(get()) }
	factory { RegisterCompanyMemberViewModel(get()) }
	factory { ProfileViewModel(get(), get(), get(), get(), get(), get()) }
	factory { WelcomeViewModel(get(), get()) }
}
fun initKoin(baseUrl: String, vararg platformModules: Module) {
	startKoin {
		modules(listOf(commonModule(baseUrl)) + platformModules)
	}
}

