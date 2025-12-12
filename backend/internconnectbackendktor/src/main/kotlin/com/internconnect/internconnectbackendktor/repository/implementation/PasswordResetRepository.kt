package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.passwordreset.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IPasswordResetRepository
import java.time.Instant
import java.util.*

class PasswordResetRepository : IPasswordResetRepository {
	override suspend fun findAll(): List<PasswordReset> {
		return dbQuery { PasswordResetEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): PasswordReset? {
		return dbQuery { PasswordResetEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(passwordReset: PasswordReset): PasswordReset? {
		return dbQuery {
			PasswordResetEntity.new(passwordReset.id) { setFrom(passwordReset, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(passwordReset: PasswordReset): PasswordReset? {
		return dbQuery {
			val e = PasswordResetEntity.findById(passwordReset.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(passwordReset.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = PasswordResetEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}