package com.internconnect.internconnectbackendktor.di

import com.internconnect.internconnectbackendktor.service.specification.*
import com.internconnect.internconnectbackendktor.service.implementation.*
import org.koin.dsl.module

val serviceModule = module {
	single<IUserService> { UserService(get()) }
	single<IPasswordAuthService> { PasswordAuthService(get(), get()) }
	single<IPasswordResetService> { PasswordResetService(get(), get()) }
	single<IRefreshTokenService> { RefreshTokenService(get(), get(), get()) }
	single<IEmailVerificationService> { EmailVerificationService(get(), get()) }
	single<IStudentService> { StudentService(get(), get()) }
	single<ICompanyService> { CompanyService(get()) }
	single<ICompanyMemberService> { CompanyMemberService(get(), get(), get()) }
	single<IInternshipService>{ InternshipService(get(), get()) }
	single<IInternshipApplicationService> { InternshipApplicationService(get(), get(), get(), get(), get()) }
	single<IAuditLogService> { AuditLogService(get(), get()) }
	single<IOAuthAccountService> { OAuthAccountService(get(), get()) }
	single<IAuthService> { AuthService(get(), get(), get(), get(), get(), get(), get(), get()) }
}
