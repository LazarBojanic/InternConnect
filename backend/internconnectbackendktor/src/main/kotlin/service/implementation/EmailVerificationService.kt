package com.internconnect.service.implementation

import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.repository.implementation.AuditLogRepository
import com.internconnect.repository.implementation.EmailVerificationRepository
import com.internconnect.service.specification.IEmailVerificationService
import java.util.UUID

class EmailVerificationService (
	private val emailVerificationRepository: EmailVerificationRepository,
) : IEmailVerificationService {
	override suspend fun getAll(): List<EmailVerification> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): EmailVerification? {
		TODO("Not yet implemented")
	}

	override suspend fun create(user: EmailVerification): EmailVerification? {
		TODO("Not yet implemented")
	}

	override suspend fun update(user: EmailVerification): EmailVerification? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}