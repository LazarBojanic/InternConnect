package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshToken
import java.time.Instant
import java.util.*

interface IRefreshTokenRepository {
	suspend fun findAll(): List<RefreshToken>
	suspend fun findById(id: UUID): RefreshToken?
	suspend fun create(refreshToken: RefreshToken): RefreshToken?
	suspend fun update(refreshToken: RefreshToken): RefreshToken?
	suspend fun delete(id: UUID): Boolean

	suspend fun findActiveByHash(hash: String): RefreshToken?
	suspend fun revokeById(id: UUID): Boolean
	suspend fun revokeBySessionId(sessionId: UUID): Int
	suspend fun revokeAllForUser(userId: UUID): Int
	suspend fun deleteExpired(now: Instant = Instant.now()): Int
}