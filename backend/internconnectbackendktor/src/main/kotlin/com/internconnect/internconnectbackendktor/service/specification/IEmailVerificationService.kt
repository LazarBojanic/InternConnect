package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.EmailVerificationJoined
import com.internconnect.internconnectbackendktor.model.raw.emailverification.EmailVerification
import java.util.*

interface IEmailVerificationService {
	suspend fun getAll(): List<EmailVerificationJoined>
	suspend fun getById(id: UUID): EmailVerificationJoined?
	suspend fun create(emailVerification: EmailVerification): EmailVerificationJoined?
	suspend fun update(emailVerification: EmailVerification): EmailVerificationJoined?
	suspend fun delete(id: UUID): Boolean
}