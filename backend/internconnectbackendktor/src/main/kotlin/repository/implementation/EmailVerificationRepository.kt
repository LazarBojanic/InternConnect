package com.internconnect.repository.implementation

import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.repository.specification.IEmailVerificationRepository
import java.util.*

class EmailVerificationRepository : IEmailVerificationRepository {
	override suspend fun findAll(): List<EmailVerification> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): EmailVerification? {
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