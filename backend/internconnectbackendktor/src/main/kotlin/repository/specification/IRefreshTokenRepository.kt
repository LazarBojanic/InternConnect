package com.internconnect.repository.specification

import com.internconnect.model.refreshtoken.RefreshToken
import java.util.UUID

interface IRefreshTokenRepository {
	suspend fun findAll(): List<RefreshToken>
	suspend fun findById(id: UUID): RefreshToken?
	suspend fun create(user: RefreshToken): RefreshToken?
	suspend fun update(user: RefreshToken): RefreshToken?
	suspend fun delete(id: UUID): Boolean
}