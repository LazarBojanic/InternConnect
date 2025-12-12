package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.User

interface IUserRepository {
	suspend fun getCurrentUser(): User?
	suspend fun setCurrentUser(user: User)
	suspend fun clear()
}