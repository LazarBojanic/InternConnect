package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.model.oauthaccount.OAuthAccountEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IOAuthAccountRepository
import java.time.Instant
import java.util.*

class OAuthAccountRepository : IOAuthAccountRepository {
	override suspend fun findAll(): List<OAuthAccount> {
		return dbQuery { OAuthAccountEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): OAuthAccount? {
		return dbQuery { OAuthAccountEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(oAuthAccount: OAuthAccount): OAuthAccount? {
		return dbQuery {
			OAuthAccountEntity.new(oAuthAccount.id) { setFrom(oAuthAccount, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(oAuthAccount: OAuthAccount): OAuthAccount? {
		return dbQuery {
			val e = OAuthAccountEntity.findById(oAuthAccount.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(oAuthAccount.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = OAuthAccountEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}