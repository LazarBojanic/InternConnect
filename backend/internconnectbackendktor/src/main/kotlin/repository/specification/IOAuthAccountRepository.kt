package com.internconnect.repository.specification

import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.model.user.User
import java.util.UUID

interface IOAuthAccountRepository {
	suspend fun findAll(): List<OAuthAccount>
	suspend fun findById(id: UUID): OAuthAccount?
	suspend fun create(user: OAuthAccount): OAuthAccount?
	suspend fun update(user: OAuthAccount): OAuthAccount?
	suspend fun delete(id: UUID): Boolean
}