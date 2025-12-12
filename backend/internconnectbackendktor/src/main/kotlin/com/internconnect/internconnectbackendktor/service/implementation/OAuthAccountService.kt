package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.OAuthAccountJoined
import com.internconnect.internconnectbackendktor.model.raw.oauthaccount.OAuthAccount
import com.internconnect.internconnectbackendktor.repository.specification.IOAuthAccountRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IOAuthAccountService
import java.util.*

class OAuthAccountService(
	private val userRepository: IUserRepository,
	private val oAuthAccountRepository: IOAuthAccountRepository,
) : IOAuthAccountService {
	override suspend fun getAll(): List<OAuthAccountJoined> {
		val oAuthAccounts = oAuthAccountRepository.findAll()
		val oAuthAccountsJoined = mutableListOf<OAuthAccountJoined>()
		for(oAuthAccount: OAuthAccount in oAuthAccounts) {
			val user = userRepository.findById(oAuthAccount.userId)
			if(user != null){
				oAuthAccountsJoined.add(oAuthAccount.join(user.join()))
			}
		}
		return oAuthAccountsJoined
	}

	override suspend fun getById(id: UUID): OAuthAccountJoined? {
		val oAuthAccount = oAuthAccountRepository.findById(id)
		if(oAuthAccount != null){
			val user = userRepository.findById(oAuthAccount.userId)
			if(user != null){
				return oAuthAccount.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(oAuthAccount: OAuthAccount): OAuthAccountJoined? {
		val created = oAuthAccountRepository.create(oAuthAccount)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(oAuthAccount: OAuthAccount): OAuthAccountJoined? {
		val updated = oAuthAccountRepository.update(oAuthAccount)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return oAuthAccountRepository.delete(id)
	}
}