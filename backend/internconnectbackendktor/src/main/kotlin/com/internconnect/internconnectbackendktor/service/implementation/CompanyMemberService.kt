package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.CompanyMemberJoined
import com.internconnect.internconnectbackendktor.model.raw.companymember.CompanyMember
import com.internconnect.internconnectbackendktor.repository.specification.*
import com.internconnect.internconnectbackendktor.service.specification.ICompanyMemberService
import java.util.*

class CompanyMemberService(
	private val userRepository: IUserRepository,
	private val companyRepository: ICompanyRepository,
	private val companyMemberRepository: ICompanyMemberRepository,
) : ICompanyMemberService {
	override suspend fun getAll(): List<CompanyMemberJoined> {
		val companyMembers = companyMemberRepository.findAll()
		val companyMembersJoined = mutableListOf<CompanyMemberJoined>()
		for(companyMember: CompanyMember in companyMembers){
			val user = userRepository.findById(companyMember.userId)
			val company = companyRepository.findById(companyMember.companyId)
			if(user != null && company != null){
				val userJoined = user.join()
				val companyJoined = company.join()
				val companyMemberJoined = companyMember.join(userJoined, companyJoined)
				companyMembersJoined.add(companyMemberJoined)
			}
		}
		return companyMembersJoined
	}

	override suspend fun getById(id: UUID): CompanyMemberJoined? {
		val companyMember = companyMemberRepository.findById(id)
		if(companyMember != null){
			val user = userRepository.findById(companyMember.userId)
			val company = companyRepository.findById(companyMember.companyId)
			if(user != null && company != null){
				return companyMember.join(user.join(), company.join())
			}
		}
		return null
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMemberJoined? {
		val created = companyMemberRepository.create(companyMember)
		if(created != null){
			val user = userRepository.findById(created.userId)
			val company = companyRepository.findById(created.companyId)
			if(user != null && company != null){
				return created.join(user.join(), company.join())
			}
		}
		return null
	}

	override suspend fun update(companyMember: CompanyMember): CompanyMemberJoined? {
		val updated = companyMemberRepository.update(companyMember)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			val company = companyRepository.findById(updated.companyId)
			if(user != null && company != null){
				return updated.join(user.join(), company.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return companyMemberRepository.delete(id)
	}
}