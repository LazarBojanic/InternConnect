package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.passwordauth.PasswordAuthEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IPasswordAuthRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class PasswordAuthRepository : IPasswordAuthRepository {
	override suspend fun findAll(): List<PasswordAuth> {
		return transaction { PasswordAuthEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): PasswordAuth? {
		return transaction { PasswordAuthEntity.findById(id)?.toDomain() }
	}

	override suspend fun findByUserId(userId: UUID): PasswordAuth? {
		return transaction { PasswordAuthEntity.findById(userId)?.toDomain() }
	}

	override suspend fun create(passwordAuth: PasswordAuth): PasswordAuth? {
		return transaction {
			PasswordAuthEntity.new(passwordAuth.userId) { setFrom(passwordAuth, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(passwordAuth: PasswordAuth): PasswordAuth? {
		return transaction {
			val e = PasswordAuthEntity.findById(passwordAuth.userId) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(passwordAuth.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = PasswordAuthEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}