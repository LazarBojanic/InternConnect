package com.internconnect.model.oauthaccount

import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime


object OAuthAccountTable : UUIDTable(name = "oauth_account") {
	val userID = reference("user_id", UserTable.id)
	val provider = varchar("provider", length = 255).nullable()
	val providerUserID = reference("provider_user_id", UserTable.id)
	val providerEmail = varchar("provider_email", length = 255).nullable()
	val encryptedAccessToken = varchar("encrypted_access_token", length = 255).nullable()
	val encryptedRefreshToken = varchar("encrypted_refresh_token", length = 255).nullable()
	val tokenExpiresAt = datetime("token_expires_at").defaultExpression(CurrentDateTime)
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}