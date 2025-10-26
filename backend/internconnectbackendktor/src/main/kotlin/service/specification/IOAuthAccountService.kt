package com.internconnect.service.specification

import com.internconnect.model.oauthaccount.OAuthAccount
import java.util.*

interface IOAuthAccountService {
	suspend fun getAll(): List<OAuthAccount>
	suspend fun getById(id: UUID): OAuthAccount?
	suspend fun create(user: OAuthAccount): OAuthAccount?
	suspend fun update(user: OAuthAccount): OAuthAccount?
	suspend fun delete(id: UUID): Boolean
}