package com.internconnect.service.specification

import com.internconnect.model.emailverification.EmailVerification
import java.util.UUID

interface IEmailVerificationService {
	suspend fun getAll(): List<EmailVerification>
	suspend fun getById(id: UUID): EmailVerification?
	suspend fun create(user: EmailVerification): EmailVerification?
	suspend fun update(user: EmailVerification): EmailVerification?
	suspend fun delete(id: UUID): Boolean
}