package com.internconnect

import com.internconnect.auth.configureSecurity
import com.internconnect.di.configureFrameworks
import com.internconnect.database.configureDatabase
import com.internconnect.http.configureHTTP
import com.internconnect.routing.configureRouting
import com.internconnect.model.auditlog.AuditLogTable
import com.internconnect.model.emailverification.EmailVerificationTable
import com.internconnect.model.oauthaccount.OAuthAccountTable
import com.internconnect.model.company.CompanyTable
import com.internconnect.model.companyinvitation.CompanyInvitationTable
import com.internconnect.model.companymember.CompanyMemberTable
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
		SchemaUtils.drop(AuditLogTable, EmailVerificationTable, OAuthAccountTable, CompanyInvitationTable, CompanyMemberTable, CompanyTable, PasswordResetTable, RefreshTokenTable, UserTable)
		SchemaUtils.create(AuditLogTable)
		SchemaUtils.create(EmailVerificationTable)
		SchemaUtils.create(OAuthAccountTable)
		SchemaUtils.create(CompanyInvitationTable)
		SchemaUtils.create(CompanyMemberTable)
		SchemaUtils.create(CompanyTable)
		SchemaUtils.create(PasswordResetTable)
		SchemaUtils.create(RefreshTokenTable)
		SchemaUtils.create(UserTable)

	}
}
