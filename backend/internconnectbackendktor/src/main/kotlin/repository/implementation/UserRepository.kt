package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.model.user.User
import com.internconnect.model.user.UserEntity
import com.internconnect.model.user.UserTable
import com.internconnect.repository.specification.IUserRepository
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class UserRepository : IUserRepository {
	override suspend fun findAll(): List<User> {
		return transaction {
			UserEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): User? {
		return transaction {
			UserEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun findByEmail(email: String): User? {
		return transaction {
			UserEntity.find { UserTable.email eq email }
				.firstOrNull()
				?.toDomain()
		}
	}

	override suspend fun create(user: User): User? {
		return transaction {
			UserEntity.new(user.id) {
				setFrom(user, MapMode.Insert)
			}.toDomain()
		}
	}

	override suspend fun update(user: User): User? {
		return transaction {
			val entity = UserEntity.findById(user.id) ?: return@transaction null
			entity.updatedAt = Instant.now()
			entity.setFrom(user.copy(updatedAt = entity.updatedAt), MapMode.Update)
			entity.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val entity = UserEntity.findById(id) ?: return@transaction false
			entity.delete()
			true
		}
	}
}