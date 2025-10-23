package com.internconnect.service.specification

import com.internconnect.model.refreshtoken.RefreshToken
import java.util.UUID

interface IRefreshTokenService {
	suspend fun getAll(): List<RefreshToken>
	suspend fun getById(id: UUID): RefreshToken?
	suspend fun create(user: RefreshToken): RefreshToken?
	suspend fun update(user: RefreshToken): RefreshToken?
	suspend fun delete(id: UUID): Boolean
}