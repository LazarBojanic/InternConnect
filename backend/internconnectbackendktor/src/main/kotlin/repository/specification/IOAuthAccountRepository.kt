package com.internconnect.repository.specification

import com.internconnect.model.oauthaccount.OAuthAccount
import java.util.*

interface IOAuthAccountRepository {
	suspend fun findAll(): List<OAuthAccount>
	suspend fun findById(id: UUID): OAuthAccount?
	suspend fun create(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun update(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun delete(id: UUID): Boolean
}