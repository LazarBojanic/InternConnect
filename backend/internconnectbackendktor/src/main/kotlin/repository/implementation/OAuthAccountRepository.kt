package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.model.oauthaccount.OAuthAccountEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IOAuthAccountRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class OAuthAccountRepository : IOAuthAccountRepository {
	override suspend fun findAll(): List<OAuthAccount> {
		return transaction { OAuthAccountEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): OAuthAccount? {
		return transaction { OAuthAccountEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(oAuthAccount: OAuthAccount): OAuthAccount? {
		return transaction {
			OAuthAccountEntity.new(oAuthAccount.id) { setFrom(oAuthAccount, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(oAuthAccount: OAuthAccount): OAuthAccount? {
		return transaction {
			val e = OAuthAccountEntity.findById(oAuthAccount.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(oAuthAccount.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = OAuthAccountEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}