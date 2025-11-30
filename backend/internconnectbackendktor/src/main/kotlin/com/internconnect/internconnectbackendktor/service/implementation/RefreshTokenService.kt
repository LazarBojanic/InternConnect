package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.RefreshTokenJoined
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.RefreshToken
import com.internconnect.internconnectbackendktor.repository.specification.IRefreshTokenRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IRefreshTokenService
import java.time.Instant
import java.util.*

class RefreshTokenService(
	private val userRepository: IUserRepository,
	private val refreshTokenRepository: IRefreshTokenRepository,
) : IRefreshTokenService {
	override suspend fun getAll(): List<RefreshTokenJoined> {
		val refreshTokens = refreshTokenRepository.findAll()
		val refreshTokensJoined = mutableListOf<RefreshTokenJoined>()
		for(refreshToken: RefreshToken in refreshTokens) {
			val user = userRepository.findById(refreshToken.userId)
			if(user != null){
				refreshTokensJoined.add(refreshToken.join(user.join()))
			}
		}
		return refreshTokensJoined
	}

	override suspend fun getById(id: UUID): RefreshTokenJoined? {
		val refreshToken = refreshTokenRepository.findById(id)
		if(refreshToken != null){
			val user = userRepository.findById(refreshToken.userId)
			if(user != null){
				return refreshToken.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(refreshToken: RefreshToken): RefreshTokenJoined? {
		val created = refreshTokenRepository.create(refreshToken)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(refreshToken: RefreshToken): RefreshTokenJoined? {
		val updated = refreshTokenRepository.update(refreshToken)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return refreshTokenRepository.delete(id)
	}

	override suspend fun findActiveByHash(hash: String): RefreshTokenJoined? {
		val refreshToken = refreshTokenRepository.findActiveByHash(hash)
		if(refreshToken != null){
			val user = userRepository.findById(refreshToken.userId)
			if(user != null){
				return refreshToken.join(user.join())
			}
		}
		return null
	}

	override suspend fun revokeById(id: UUID): Boolean {
		return refreshTokenRepository.revokeById(id)
	}

	override suspend fun revokeBySessionId(sessionId: UUID): Int {
		return refreshTokenRepository.revokeBySessionId(sessionId)
	}

	override suspend fun revokeAllForUser(userId: UUID): Int {
		return refreshTokenRepository.revokeAllForUser(userId)
	}

	override suspend fun deleteExpired(now: Instant): Int {
		return refreshTokenRepository.deleteExpired(now)
	}
}