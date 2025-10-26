package com.internconnect.service.implementation

import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.repository.specification.IRefreshTokenRepository
import com.internconnect.service.specification.IRefreshTokenService
import java.util.*

class RefreshTokenService (
	private val refreshTokenRepository: IRefreshTokenRepository,
) : IRefreshTokenService{
	override suspend fun getAll(): List<RefreshToken> {
		return refreshTokenRepository.findAll()
	}

	override suspend fun getById(id: UUID): RefreshToken? {
		return refreshTokenRepository.findById(id)
	}

	override suspend fun create(refreshToken: RefreshToken): RefreshToken? {
		return refreshTokenRepository.create(refreshToken)
	}

	override suspend fun update(refreshToken: RefreshToken): RefreshToken? {
		return refreshTokenRepository.update(refreshToken)
	}

	override suspend fun delete(id: UUID): Boolean {
		return refreshTokenRepository.delete(id)
	}
}