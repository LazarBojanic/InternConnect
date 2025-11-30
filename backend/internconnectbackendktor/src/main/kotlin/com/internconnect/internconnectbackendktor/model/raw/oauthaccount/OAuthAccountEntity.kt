package com.internconnect.internconnectbackendktor.model.raw.oauthaccount

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class OAuthAccountEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<OAuthAccountEntity>(OAuthAccountTable)

	var userId by OAuthAccountTable.userId
	var provider by OAuthAccountTable.provider
	var providerUserId by OAuthAccountTable.providerUserId
	var providerEmail by OAuthAccountTable.providerEmail
	var encryptedAccessToken by OAuthAccountTable.encryptedAccessToken
	var encryptedRefreshToken by OAuthAccountTable.encryptedRefreshToken
	var tokenExpiresAt by OAuthAccountTable.tokenExpiresAt
	var createdAt by OAuthAccountTable.createdAt
	var updatedAt by OAuthAccountTable.updatedAt
}


