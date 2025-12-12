package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.emailverification.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IEmailVerificationRepository
import java.time.Instant
import java.util.*

class EmailVerificationRepository : IEmailVerificationRepository {
	override suspend fun findAll(): List<EmailVerification> {
		return dbQuery { EmailVerificationEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): EmailVerification? {
		return dbQuery { EmailVerificationEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(emailVerification: EmailVerification): EmailVerification? {
		return dbQuery {
			EmailVerificationEntity.new(emailVerification.id) { setFrom(emailVerification, MapMode.Insert) }
				.toDomain()
		}
	}

	override suspend fun update(emailVerification: EmailVerification): EmailVerification? {
		return dbQuery {
			val e = EmailVerificationEntity.findById(emailVerification.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(emailVerification.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = EmailVerificationEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}