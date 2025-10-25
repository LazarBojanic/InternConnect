package com.internconnect

import com.internconnect.auth.configureSecurity
import com.internconnect.di.configureFrameworks
import com.internconnect.database.configureDatabase
import com.internconnect.http.configureHTTP
import com.internconnect.routing.configureRouting
import com.internconnect.model.auditlog.AuditLogTable
import com.internconnect.model.emailverification.EmailVerificationTable
import com.internconnect.model.oauthaccount.OAuthAccountTable
import com.internconnect.model.organization.OrganizationTable
import com.internconnect.model.organizationinvitation.OrganizationInvitationTable
import com.internconnect.model.organizationmember.OrganizationMemberTable
import com.internconnect.model.passwordreset.PasswordResetTable
import com.internconnect.model.refreshtoken.RefreshTokenTable
import com.internconnect.model.user.UserTable
import com.internconnect.util.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
	EngineMain.main(args)
}

fun Application.module() {
	configureHTTP()
	configureSecurity()
	configureSerialization()
	configureDatabase()
	configureFrameworks()
	configureRouting()
	transaction {
		SchemaUtils.drop(AuditLogTable, EmailVerificationTable, OAuthAccountTable, OrganizationInvitationTable, OrganizationMemberTable, OrganizationTable, PasswordResetTable, RefreshTokenTable, UserTable)
		SchemaUtils.create(AuditLogTable)
		SchemaUtils.create(EmailVerificationTable)
		SchemaUtils.create(OAuthAccountTable)
		SchemaUtils.create(OrganizationInvitationTable)
		SchemaUtils.create(OrganizationMemberTable)
		SchemaUtils.create(OrganizationTable)
		SchemaUtils.create(PasswordResetTable)
		SchemaUtils.create(RefreshTokenTable)
		SchemaUtils.create(UserTable)

	}
}
