package com.internconnect.service.specification

import com.internconnect.model.refreshtoken.RefreshToken
import java.time.Instant
import java.util.*

interface IRefreshTokenService {
	suspend fun getAll(): List<RefreshToken>
	suspend fun getById(id: UUID): RefreshToken?
	suspend fun create(refreshToken: RefreshToken): RefreshToken?
	suspend fun update(refreshToken: RefreshToken): RefreshToken?
	suspend fun delete(id: UUID): Boolean

	suspend fun findActiveByHash(hash: String): RefreshToken?
	suspend fun revokeById(id: UUID): Boolean
	suspend fun revokeBySessionId(sessionId: UUID): Int
	suspend fun revokeAllForUser(userId: UUID): Int
	suspend fun deleteExpired(now: Instant = Instant.now()): Int
}