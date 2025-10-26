package com.internconnect.service.implementation

import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.repository.specification.IRefreshTokenRepository
import com.internconnect.service.specification.IRefreshTokenService
import java.util.*

class RefreshTokenService (
	private val refreshTokenRepository: IRefreshTokenRepository,
) : IRefreshTokenService{
	override suspend fun getAll(): List<RefreshToken> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): RefreshToken? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: RefreshToken): RefreshToken? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: RefreshToken): RefreshToken? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}