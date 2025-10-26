package com.internconnect.repository.implementation

import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.repository.specification.IRefreshTokenRepository
import java.util.*

class RefreshTokenRepository : IRefreshTokenRepository {
	override suspend fun findAll(): List<RefreshToken> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): RefreshToken? {
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