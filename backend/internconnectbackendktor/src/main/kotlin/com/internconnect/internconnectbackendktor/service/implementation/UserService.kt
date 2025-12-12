package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.UserJoined
import com.internconnect.internconnectbackendktor.model.raw.user.User
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyMemberRepository
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyRepository
import com.internconnect.internconnectbackendktor.repository.specification.IStudentRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IUserService
import java.util.*

class UserService(
	private val userRepository: IUserRepository,
) : IUserService {
	override suspend fun getAll(): List<UserJoined> {
		val users = userRepository.findAll()
		val usersJoined = mutableListOf<UserJoined>()
		for(user: User in users){
			usersJoined.add(user.join())
		}
		return usersJoined
	}

	override suspend fun getById(id: UUID): UserJoined? {
		val user = userRepository.findById(id)
		if(user != null){
			return user.join()
		}
		return null
	}

	override suspend fun getByEmail(email: String): UserJoined? {
		val user = userRepository.findByEmail(email)
		if(user != null){
			return user.join()
		}
		return null
	}

	override suspend fun create(user: User): UserJoined? {
		val created = userRepository.create(user)
		if(created != null){
			return created.join()
		}
		return null
	}

	override suspend fun update(user: User): UserJoined? {
		val updated = userRepository.update(user)
		if(updated != null){
			return updated.join()
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return userRepository.delete(id)
	}
}