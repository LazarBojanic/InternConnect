package com.internconnect.di

import com.internconnect.repository.implementation.*
import com.internconnect.repository.specification.*
import org.koin.dsl.module

val repositoryModule = module{
	single<IAuditLogRepository>{ AuditLogRepository() }
	single<IEmailVerificationRepository>{ EmailVerificationRepository() }
	single<IOAuthAccountRepository>{ OAuthAccountRepository() }
	single<IUserRepository>{ UserRepository() }
	single<IStudentRepository>{ StudentRepository() }
	single<ICompanyInvitationRepository>{ CompanyInvitationRepository() }
	single<ICompanyMemberRepository>{ CompanyMemberRepository() }
	single<ICompanyRepository>{ CompanyRepository() }
	single<IPasswordResetRepository>{ PasswordResetRepository() }
	single<IRefreshTokenRepository>{ RefreshTokenRepository() }
	single<IPasswordAuthRepository>{ PasswordAuthRepository() }
}