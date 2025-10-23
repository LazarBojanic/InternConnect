package com.internconnect.repository.implementation

import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.repository.specification.IOAuthAccountRepository
import java.util.UUID

class OAuthAccountRepository : IOAuthAccountRepository {
	override suspend fun findAll(): List<OAuthAccount> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): OAuthAccount? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: OAuthAccount): OAuthAccount? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: OAuthAccount): OAuthAccount? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}