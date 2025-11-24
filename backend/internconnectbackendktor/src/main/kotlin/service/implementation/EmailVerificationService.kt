package com.internconnect.service.implementation

import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.repository.specification.IEmailVerificationRepository
import com.internconnect.service.specification.IEmailVerificationService
import java.util.*

class EmailVerificationService(
	private val emailVerificationRepository: IEmailVerificationRepository,
) : IEmailVerificationService {
	override suspend fun getAll(): List<EmailVerification> {
		return emailVerificationRepository.findAll()
	}

	override suspend fun getById(id: UUID): EmailVerification? {
		return emailVerificationRepository.findById(id)
	}

	override suspend fun create(emailVerification: EmailVerification): EmailVerification? {
		return emailVerificationRepository.create(emailVerification)
	}

	override suspend fun update(emailVerification: EmailVerification): EmailVerification? {
		return emailVerificationRepository.update(emailVerification)
	}

	override suspend fun delete(id: UUID): Boolean {
		return emailVerificationRepository.delete(id)
	}
}