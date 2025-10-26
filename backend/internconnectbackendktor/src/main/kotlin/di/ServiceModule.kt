package com.internconnect.di

import com.internconnect.service.implementation.*
import com.internconnect.service.specification.*
import org.koin.dsl.module

val serviceModule = module{
	single<IUserService>{ UserService(get()) }
	single<IPasswordAuthService>{ PasswordAuthService(get()) }
	single<IPasswordResetService>{ PasswordResetService(get()) }
	single<IRefreshTokenService>{ RefreshTokenService(get()) }
	single<IEmailVerificationService>{ EmailVerificationService(get()) }
	single<IStudentService>{ StudentService(get()) }
	single<ICompanyService>{ CompanyService(get()) }
	single<ICompanyMemberService>{ CompanyMemberService(get()) }
	single<ICompanyInvitationService>{ CompanyInvitationService(get()) }
	single<IAuditLogService>{ AuditLogService(get()) }
	single<IOAuthAccountService>{ OAuthAccountService(get()) }
	single<IAuthService>{ AuthService(get(), get(), get(), get(), get(), get()) }
}
