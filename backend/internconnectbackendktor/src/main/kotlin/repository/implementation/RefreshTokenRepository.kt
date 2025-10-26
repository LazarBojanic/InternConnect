package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.refreshtoken.RefreshTokenEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IRefreshTokenRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class RefreshTokenRepository : IRefreshTokenRepository {
	override suspend fun findAll(): List<RefreshToken> {
		return transaction { RefreshTokenEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): RefreshToken? {
		return transaction { RefreshTokenEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(refreshToken: RefreshToken): RefreshToken? {
		return transaction {
			RefreshTokenEntity.new(refreshToken.id) { setFrom(refreshToken, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(refreshToken: RefreshToken): RefreshToken? {
		return transaction {
			val e = RefreshTokenEntity.findById(refreshToken.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(refreshToken.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = RefreshTokenEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}