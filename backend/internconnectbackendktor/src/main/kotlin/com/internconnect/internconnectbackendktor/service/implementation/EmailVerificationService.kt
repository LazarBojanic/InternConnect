package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.EmailVerificationJoined
import com.internconnect.internconnectbackendktor.model.raw.emailverification.EmailVerification
import com.internconnect.internconnectbackendktor.repository.specification.IEmailVerificationRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IEmailVerificationService
import java.util.*

class EmailVerificationService(
	private val userRepository: IUserRepository,
	private val emailVerificationRepository: IEmailVerificationRepository,
) : IEmailVerificationService {
	override suspend fun getAll(): List<EmailVerificationJoined> {
		val emailVerifications = emailVerificationRepository.findAll()
		val emailVerificationsJoined = mutableListOf<EmailVerificationJoined>()
		for(emailVerification: EmailVerification in emailVerifications) {
			val user = userRepository.findById(emailVerification.userId)
			if(user != null){
				emailVerificationsJoined.add(emailVerification.join(user.join()))
			}
		}
		return emailVerificationsJoined
	}

	override suspend fun getById(id: UUID): EmailVerificationJoined? {
		val emailVerification = emailVerificationRepository.findById(id)
		if(emailVerification != null){
			val user = userRepository.findById(emailVerification.userId)
			if(user != null){
				return emailVerification.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(emailVerification: EmailVerification): EmailVerificationJoined? {
		val created = emailVerificationRepository.create(emailVerification)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(emailVerification: EmailVerification): EmailVerificationJoined? {
		val updated = emailVerificationRepository.update(emailVerification)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return emailVerificationRepository.delete(id)
	}
}