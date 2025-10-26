package com.internconnect.di

import com.internconnect.repository.implementation.*
import com.internconnect.repository.specification.*
import org.koin.dsl.module

val repositoryModule = module{
	single<IUserRepository>{ UserRepository() }
	single<IPasswordAuthRepository>{ PasswordAuthRepository() }
	single<IPasswordResetRepository>{ PasswordResetRepository() }
	single<IRefreshTokenRepository>{ RefreshTokenRepository() }
	single<IEmailVerificationRepository>{ EmailVerificationRepository() }
	single<IStudentRepository>{ StudentRepository() }
	single<ICompanyRepository>{ CompanyRepository() }
	single<ICompanyMemberRepository>{ CompanyMemberRepository() }
	single<ICompanyInvitationRepository>{ CompanyInvitationRepository() }
	single<IAuditLogRepository>{ AuditLogRepository() }
	single<IOAuthAccountRepository>{ OAuthAccountRepository() }
}