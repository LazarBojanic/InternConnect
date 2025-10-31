package com.internconnect.service.implementation

import com.internconnect.dto.CompanyMemberProfileDto
import com.internconnect.dto.StudentProfileDto
import com.internconnect.model.user.User
import com.internconnect.repository.specification.ICompanyMemberRepository
import com.internconnect.repository.specification.ICompanyRepository
import com.internconnect.repository.specification.IStudentRepository
import com.internconnect.repository.specification.IUserRepository
import com.internconnect.service.specification.IUserService
import java.util.*

class UserService (
	private val userRepository: IUserRepository,
	private val studentRepository: IStudentRepository,
	private val companyRepository: ICompanyRepository,
	private val companyMemberRepository: ICompanyMemberRepository,
) : IUserService {
	override suspend fun getAll(): List<User> {
		return userRepository.findAll()
	}

	override suspend fun getById(id: UUID): User? {
		return userRepository.findById(id)
	}
	override suspend fun getByEmail(email: String): User? {
		return userRepository.findByEmail(email)
	}

	override suspend fun getStudentProfileById(userId: UUID): StudentProfileDto? {
		val user = userRepository.findById(userId)
		var studentProfileDto: StudentProfileDto? = null
		if(user != null){
			val student = studentRepository.findById(userId)
			if(student != null){
				studentProfileDto = StudentProfileDto(
					userId = userId.toString(),
					email = user.email,
					firstName = student.firstName,
					lastName = student.lastName,
					fullName = user.fullName,
					userRole = user.userRole.name,
					isEmailVerified = user.isEmailVerified,
					userStatus = user.userStatus.name,
					schoolName = student.schoolName,
					grade = student.grade,
					bio = student.bio,
					interests = student.interests,
					avatarUrl = student.avatarUrl
				)
			}
		}
		return studentProfileDto
	}

	override suspend fun getCompanyMemberProfileByUserId(userId: UUID): CompanyMemberProfileDto? {
		val user = userRepository.findById(userId)

		val companyMember = companyMemberRepository.findByUserId(userId)

		var companyMemberProfileDto: CompanyMemberProfileDto? = null

		if(user != null && companyMember != null){
			val company = companyRepository.findById(companyMember.companyId)

			if(company != null){
				companyMemberProfileDto = CompanyMemberProfileDto(
					userId = userId.toString(),
					userEmail = user.email,
					userFullName = user.fullName,
					userRole = user.userRole.name,
					isEmailVerified = user.isEmailVerified,
					userStatus = user.userStatus.name,
					companyName = company.name,
					companyIndustry = company.industry,
					companyMemberRole = companyMember.companyMemberRole.name,
					companyMemberStatus = companyMember.companyMemberStatus.name,
					joinedAt = companyMember.joinedAt.toString(),
					website = company.website,
					logoUrl = company.logoUrl,
					hqCountry = company.hqCountry,
					city = company.city,
					about = company.about
				)
			}
		}
		return companyMemberProfileDto
	}

	override suspend fun create(user: User): User? {
		return userRepository.create(user)
	}

	override suspend fun update(user: User): User? {
		return userRepository.update(user)
	}

	override suspend fun delete(id: UUID): Boolean {
		return userRepository.delete(id)
	}
}