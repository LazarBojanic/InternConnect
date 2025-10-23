package com.internconnect.repository.specification

import com.internconnect.model.emailverification.EmailVerification
import java.util.UUID

interface IEmailVerificationRepository {
	suspend fun findAll(): List<EmailVerification>
	suspend fun findById(id: UUID): EmailVerification?
	suspend fun create(user: EmailVerification): EmailVerification?
	suspend fun update(user: EmailVerification): EmailVerification?
	suspend fun delete(id: UUID): Boolean
}