package com.internconnect.repository.specification

import com.internconnect.model.refreshtoken.RefreshToken
import java.util.*

interface IRefreshTokenRepository {
	suspend fun findAll(): List<RefreshToken>
	suspend fun findById(id: UUID): RefreshToken?
	suspend fun create(refreshToken: RefreshToken): RefreshToken?
	suspend fun update(refreshToken: RefreshToken): RefreshToken?
	suspend fun delete(id: UUID): Boolean
}