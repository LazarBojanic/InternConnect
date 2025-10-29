package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.refreshtoken.RefreshTokenEntity
import com.internconnect.model.refreshtoken.RefreshTokenTable
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IRefreshTokenRepository
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.greater
import org.jetbrains.exposed.v1.core.less
import org.jetbrains.exposed.v1.core.isNull
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class RefreshTokenRepository : IRefreshTokenRepository {
	override suspend fun findAll(): List<RefreshToken> {
		return dbQuery {
			RefreshTokenEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): RefreshToken? {
		return dbQuery {
			RefreshTokenEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun create(refreshToken: RefreshToken): RefreshToken? {
		return dbQuery {
			RefreshTokenEntity.new(refreshToken.id) { setFrom(refreshToken, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(refreshToken: RefreshToken): RefreshToken? {
		return dbQuery {
			val e = RefreshTokenEntity.findById(refreshToken.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(refreshToken.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = RefreshTokenEntity.findById(id) ?: return@dbQuery false
			e.delete()
			true
		}
	}

	override suspend fun findActiveByHash(hash: String): RefreshToken? {
		return dbQuery {
			RefreshTokenEntity.find { (RefreshTokenTable.hash eq hash) and (RefreshTokenTable.revokedAt.isNull()) and (RefreshTokenTable.expiresAt greater Instant.now()) }
				.limit(1)
				.firstOrNull()
				?.toDomain()
		}
	}

	override suspend fun revokeById(id: UUID): Boolean {
		return dbQuery {
			val e = RefreshTokenEntity.findById(id) ?: return@dbQuery false
			e.revokedAt = Instant.now(); e.updatedAt = Instant.now(); true
		}
	}

	override suspend fun revokeBySessionId(sessionId: UUID): Int {
		return dbQuery {
			RefreshTokenEntity.find { RefreshTokenTable.sessionId eq sessionId }.sumOf { e ->
				e.revokedAt = Instant.now(); e.updatedAt = Instant.now(); 1
			}
		}
	}

	override suspend fun revokeAllForUser(userId: UUID): Int {
		return dbQuery {
			RefreshTokenEntity.find { RefreshTokenTable.userId eq userId }.sumOf { e ->
				e.revokedAt = Instant.now(); e.updatedAt = Instant.now(); 1
			}
		}
	}

	override suspend fun deleteExpired(now: Instant): Int  {
		return dbQuery {
			RefreshTokenEntity.find { RefreshTokenTable.expiresAt less now }.count().also {
				RefreshTokenEntity.find { RefreshTokenTable.expiresAt less now }.forEach { it.delete() }
			}.toInt()
		}
	}
}