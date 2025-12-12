package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.UserDao
import com.internconnect.internconnectfrontendclient.data.model.raw.User
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IUserRepository
import kotlinx.coroutines.flow.first

class UserRepository(
	private val userDao: UserDao
) : IUserRepository {
	override suspend fun getCurrentUser(): User? {
		return userDao.findFirst().first()
	}

	override suspend fun setCurrentUser(user: User) {
		userDao.upsert(user)
	}

	override suspend fun clear() {
		userDao.clearAll()
	}
}