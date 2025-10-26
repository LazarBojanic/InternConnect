package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.model.emailverification.EmailVerificationEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IEmailVerificationRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class EmailVerificationRepository : IEmailVerificationRepository {
	override suspend fun findAll(): List<EmailVerification> {
		return transaction { EmailVerificationEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): EmailVerification? {
		return transaction { EmailVerificationEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(emailVerification: EmailVerification): EmailVerification? {
		return transaction {
			EmailVerificationEntity.new(emailVerification.id) { setFrom(emailVerification, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(emailVerification: EmailVerification): EmailVerification? {
		return transaction {
			val e = EmailVerificationEntity.findById(emailVerification.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(emailVerification.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = EmailVerificationEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}