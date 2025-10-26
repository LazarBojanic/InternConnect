package com.internconnect.service.specification

import com.internconnect.model.oauthaccount.OAuthAccount
import java.util.*

interface IOAuthAccountService {
	suspend fun getAll(): List<OAuthAccount>
	suspend fun getById(id: UUID): OAuthAccount?
	suspend fun create(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun update(oAuthAccount: OAuthAccount): OAuthAccount?
	suspend fun delete(id: UUID): Boolean
}