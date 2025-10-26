package com.internconnect.service.specification

import com.internconnect.model.emailverification.EmailVerification
import java.util.*

interface IEmailVerificationService {
	suspend fun getAll(): List<EmailVerification>
	suspend fun getById(id: UUID): EmailVerification?
	suspend fun create(emailVerification: EmailVerification): EmailVerification?
	suspend fun update(emailVerification: EmailVerification): EmailVerification?
	suspend fun delete(id: UUID): Boolean
}