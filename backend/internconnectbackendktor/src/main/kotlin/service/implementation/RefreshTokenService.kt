package com.internconnect.service.implementation

import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.RefreshTokenRepository
import com.internconnect.repository.specification.IRefreshTokenRepository
import com.internconnect.service.specification.IRefreshTokenService
import java.util.UUID

class RefreshTokenService (
	private val refreshTokenRepository: RefreshTokenRepository,
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