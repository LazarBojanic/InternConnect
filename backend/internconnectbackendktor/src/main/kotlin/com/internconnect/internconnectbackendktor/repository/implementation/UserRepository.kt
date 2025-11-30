package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.user.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import org.jetbrains.exposed.v1.core.eq
import java.time.Instant
import java.util.*

class UserRepository : IUserRepository {
	override suspend fun findAll(): List<User> {
		return dbQuery {
			UserEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): User? {
		return dbQuery {
			UserEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun findByEmail(email: String): User? {
		return dbQuery {
			UserEntity.find { UserTable.email eq email }
				.firstOrNull()
				?.toDomain()
		}
	}

	override suspend fun create(user: User): User? {
		return dbQuery {
			UserEntity.new(user.id) {
				setFrom(user, MapMode.Insert)
			}.toDomain()
		}
	}

	override suspend fun update(user: User): User? {
		return dbQuery {
			val entity = UserEntity.findById(user.id) ?: return@dbQuery null
			entity.updatedAt = Instant.now()
			entity.setFrom(user.copy(updatedAt = entity.updatedAt), MapMode.Update)
			entity.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val entity = UserEntity.findById(id) ?: return@dbQuery false
			entity.delete()
			true
		}
	}
}