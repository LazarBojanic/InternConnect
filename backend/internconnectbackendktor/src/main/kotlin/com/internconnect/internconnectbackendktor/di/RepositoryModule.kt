package com.internconnect.internconnectbackendktor.di

import com.internconnect.internconnectbackendktor.repository.specification.*
import com.internconnect.internconnectbackendktor.repository.implementation.*
import org.koin.dsl.module

val repositoryModule = module {
	single<IUserRepository> { UserRepository() }
	single<IPasswordAuthRepository> { PasswordAuthRepository() }
	single<IPasswordResetRepository> { PasswordResetRepository() }
	single<IRefreshTokenRepository> { RefreshRefreshTokenRepository() }
	single<IEmailVerificationRepository> { EmailVerificationRepository() }
	single<IStudentRepository> { StudentRepository() }
	single<ICompanyRepository> { CompanyRepository() }
	single<ICompanyMemberRepository> { CompanyMemberRepository() }
	single<IInternshipRepository>{ InternshipRepository() }
	single<IInternshipApplicationRepository> { InternshipApplicationRepository() }
	single<IAuditLogRepository> { AuditLogRepository() }
	single<IOAuthAccountRepository> { OAuthAccountRepository() }
	single<ISessionRepository> { SessionRepository() }
}