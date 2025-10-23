package com.internconnect.service.implementation

import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.OAuthAccountRepository
import com.internconnect.repository.specification.IOAuthAccountRepository
import com.internconnect.service.specification.IOAuthAccountService
import java.util.UUID

class OAuthAccountService (
	private val oAuthAccountRepository: OAuthAccountRepository,
): IOAuthAccountService {
	override suspend fun getAll(): List<OAuthAccount> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): OAuthAccount? {
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