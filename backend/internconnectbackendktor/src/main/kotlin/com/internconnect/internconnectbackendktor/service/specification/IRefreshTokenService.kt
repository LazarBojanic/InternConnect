package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.RefreshTokenJoined
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshToken
import java.time.Instant
import java.util.*

interface IRefreshTokenService {
	suspend fun getAll(): List<RefreshTokenJoined>
	suspend fun getById(id: UUID): RefreshTokenJoined?
	suspend fun create(refreshToken: RefreshToken): RefreshTokenJoined?
	suspend fun update(refreshToken: RefreshToken): RefreshTokenJoined?
	suspend fun delete(id: UUID): Boolean

	suspend fun findActiveByHash(hash: String): RefreshTokenJoined?
	suspend fun revokeById(id: UUID): Boolean
	suspend fun revokeBySessionId(sessionId: UUID): Int
	suspend fun revokeAllForUser(userId: UUID): Int
	suspend fun deleteExpired(now: Instant = Instant.now()): Int
}