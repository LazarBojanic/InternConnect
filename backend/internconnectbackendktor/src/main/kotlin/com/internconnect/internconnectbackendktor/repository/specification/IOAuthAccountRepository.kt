package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.oauthaccount.OAuthAccount
import java.util.*

interface IOAuthAccountRepository {
	suspend fun findAll(): List<OAuthAccount>
	suspend fun findById(id: UUID): OAuthAccount?
	suspend fun create(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun update(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun delete(id: UUID): Boolean
}