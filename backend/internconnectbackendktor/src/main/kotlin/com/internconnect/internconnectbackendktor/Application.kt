package com.internconnect.internconnectbackendktor


import com.internconnect.internconnectbackendktor.auth.configureSecurity
import com.internconnect.internconnectbackendktor.database.configureDatabase
import com.internconnect.internconnectbackendktor.di.configureFrameworks
import com.internconnect.internconnectbackendktor.http.configureHTTP
import com.internconnect.internconnectbackendktor.model.raw.auditlog.AuditLogTable
import com.internconnect.internconnectbackendktor.model.raw.company.CompanyTable
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMemberTable
import com.internconnect.internconnectbackendktor.model.raw.emailverification.EmailVerificationTable
import com.internconnect.internconnectbackendktor.model.raw.internship.InternshipTable
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationTable
import com.internconnect.internconnectbackendktor.model.raw.oauthaccount.OAuthAccountTable
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.PasswordAuthTable
import com.internconnect.internconnectbackendktor.model.raw.passwordreset.PasswordResetTable
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshTokenTable
import com.internconnect.internconnectbackendktor.model.raw.session.SessionTable
import com.internconnect.internconnectbackendktor.model.raw.student.StudentTable
import com.internconnect.internconnectbackendktor.model.raw.user.UserTable
import com.internconnect.internconnectbackendktor.routing.configureRouting
import com.internconnect.internconnectbackendktor.routing.configureStaticRouting
import com.internconnect.internconnectbackendktor.util.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
	EngineMain.main(args)
}

fun Application.module() {
	val dev: Boolean = environment.config.property("ktor.development") .getString().toBoolean()
	configureHTTP()
	configureSerialization()
	configureDatabase()
	configureFrameworks()
	configureSecurity()
	configureRouting()
	configureStaticRouting()

	val allTables = arrayOf(
		AuditLogTable,
		CompanyTable,
		CompanyMemberTable,
		EmailVerificationTable,
		InternshipTable,
		InternshipApplicationTable,
		OAuthAccountTable,
		PasswordAuthTable,
		PasswordResetTable,
		SessionTable,
		RefreshTokenTable,
		StudentTable,
		UserTable,
	)
	transaction {
		if(dev){
			exec("DROP SCHEMA IF EXISTS public CASCADE;")
			exec("CREATE SCHEMA public;")
		}
	}

	transaction{
		SchemaUtils.createMissingTablesAndColumns(*allTables)
	}
}
