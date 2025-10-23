package com.internconnect.di

import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.EmailVerificationRepository
import com.internconnect.repository.implementation.OAuthAccountRepository
import com.internconnect.repository.implementation.OrganizationInvitationRepository
import com.internconnect.repository.implementation.OrganizationMemberRepository
import com.internconnect.repository.implementation.OrganizationRepository
import com.internconnect.repository.implementation.PasswordResetRepository
import com.internconnect.repository.implementation.RefreshTokenRepository
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.repository.specification.IAuditLogRepository
import com.internconnect.repository.specification.IEmailVerificationRepository
import com.internconnect.repository.specification.IOAuthAccountRepository
import com.internconnect.repository.specification.IOrganizationInvitationRepository
import com.internconnect.repository.specification.IOrganizationMemberRepository
import com.internconnect.repository.specification.IOrganizationRepository
import com.internconnect.repository.specification.IPasswordResetRepository
import com.internconnect.repository.specification.IRefreshTokenRepository
import com.internconnect.repository.specification.IUserRepository
import org.koin.dsl.module

val repositoryModule = module{
	single<IAuditLogRepository>{ AuditLogRepository() }
	single<IEmailVerificationRepository>{ EmailVerificationRepository() }
	single<IOAuthAccountRepository>{ OAuthAccountRepository() }
	single<IOrganizationInvitationRepository>{ OrganizationInvitationRepository() }
	single<IOrganizationMemberRepository>{ OrganizationMemberRepository() }
	single<IOrganizationRepository>{ OrganizationRepository() }
	single<IPasswordResetRepository>{ PasswordResetRepository() }
	single<IRefreshTokenRepository>{ RefreshTokenRepository() }
	single<IUserRepository>{ UserRepository() }
}