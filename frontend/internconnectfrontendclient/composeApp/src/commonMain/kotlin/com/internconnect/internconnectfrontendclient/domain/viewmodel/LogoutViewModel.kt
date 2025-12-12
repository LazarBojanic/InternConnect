package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.store.ITokenDataStore
import com.internconnect.internconnectfrontendclient.domain.repository.implementation.StudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyMemberRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.ICompanyRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IStudentRepository
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IUserRepository
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class LogoutViewModel(
	private val api: IAppApi,
	private val tokenStore: ITokenDataStore,
	private val userRepository: IUserRepository,
	private val studentRepository: IStudentRepository,
	private val companyRepository: ICompanyRepository,
	private val companyMemberRepository: ICompanyMemberRepository
) {
	private val scope = CoroutineScope(Dispatchers.IO)

	fun logout(onDone: (() -> Unit)? = null) {
		scope.launch {
			try { api.logout() } catch (_: Throwable) { /* ignore */ }
			tokenStore.clear()
			userRepository.clear()
			studentRepository.clear()
			companyRepository.clear()
			companyMemberRepository.clear()
			onDone?.invoke()
		}
	}
}