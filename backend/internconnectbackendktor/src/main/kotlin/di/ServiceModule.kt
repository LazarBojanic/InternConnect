package com.internconnect.di

import com.internconnect.service.implementation.AuditLogService
import com.internconnect.service.implementation.AuthService
import com.internconnect.service.implementation.EmailVerificationService
import com.internconnect.service.implementation.OAuthAccountService
import com.internconnect.service.implementation.OrganizationInvitationService
import com.internconnect.service.implementation.OrganizationMemberService
import com.internconnect.service.implementation.OrganizationService
import com.internconnect.service.implementation.PasswordResetService
import com.internconnect.service.implementation.RefreshTokenService
import com.internconnect.service.implementation.UserService
import com.internconnect.service.specification.IAuditLogService
import com.internconnect.service.specification.IAuthService
import com.internconnect.service.specification.IEmailVerificationService
import com.internconnect.service.specification.IOAuthAccountService
import com.internconnect.service.specification.IOrganizationInvitationService
import com.internconnect.service.specification.IOrganizationMemberService
import com.internconnect.service.specification.IOrganizationService
import com.internconnect.service.specification.IPasswordResetService
import com.internconnect.service.specification.IRefreshTokenService
import com.internconnect.service.specification.IUserService
import org.koin.dsl.module

val serviceModule = module{
	single<IAuditLogService>{ AuditLogService(get()) }
	single<IEmailVerificationService>{ EmailVerificationService(get()) }
	single<IOAuthAccountService>{ OAuthAccountService(get()) }
	single<IOrganizationInvitationService>{ OrganizationInvitationService(get()) }
	single<IOrganizationMemberService>{ OrganizationMemberService(get()) }
	single<IOrganizationService>{ OrganizationService(get()) }
	single<IPasswordResetService>{ PasswordResetService(get()) }
	single<IRefreshTokenService>{ RefreshTokenService(get()) }
	single<IUserService>{ UserService(get()) }
	single<IAuthService>{ AuthService(get()) }
}