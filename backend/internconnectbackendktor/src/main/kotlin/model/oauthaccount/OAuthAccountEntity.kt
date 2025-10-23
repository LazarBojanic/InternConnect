package com.internconnect.model.oauthaccount

import com.internconnect.model.emailverification.EmailVerificationTable
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.UUID

class OAuthAccountEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<OAuthAccountEntity>(OAuthAccountTable)
	var userID by OAuthAccountTable.userID
	var provider by OAuthAccountTable.provider
	var providerUserID by OAuthAccountTable.providerUserID
	var providerEmail by OAuthAccountTable.providerEmail
	var encryptedAccessToken by OAuthAccountTable.encryptedAccessToken
	var encryptedRefreshToken by OAuthAccountTable.encryptedRefreshToken
	var tokenExpiresAt by OAuthAccountTable.tokenExpiresAt
	var createdAt by OAuthAccountTable.createdAt
	var updatedAt by OAuthAccountTable.updatedAt
}


