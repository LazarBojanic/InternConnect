package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.emailverification.EmailVerification
import java.util.*

interface IEmailVerificationRepository {
	suspend fun findAll(): List<EmailVerification>
	suspend fun findById(id: UUID): EmailVerification?
	suspend fun create(emailVerification: EmailVerification): EmailVerification?
	suspend fun update(emailVerification: EmailVerification): EmailVerification?
	suspend fun delete(id: UUID): Boolean
}