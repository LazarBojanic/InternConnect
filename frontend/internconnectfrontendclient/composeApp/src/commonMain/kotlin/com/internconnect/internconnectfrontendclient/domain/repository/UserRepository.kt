package com.internconnect.internconnectfrontendclient.domain.repository

import com.internconnect.internconnectfrontendclient.data.dao.CompanyMemberProfileDao
import com.internconnect.internconnectfrontendclient.data.dao.StudentProfileDao
import com.internconnect.internconnectfrontendclient.data.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.response.StudentDto
import com.internconnect.internconnectfrontendclient.data.toDomain
import com.internconnect.internconnectfrontendclient.data.toDto
import kotlinx.coroutines.flow.firstOrNull

class UserRepository(
	private val studentDao: StudentProfileDao,
	private val companyDao: CompanyMemberProfileDao,
) : IUserRepository {

	override suspend fun getCurrentStudentProfile(): StudentDto? {

		return studentDao.findAll().firstOrNull()?.firstOrNull()?.toDto()
	}

	override suspend fun getCurrentCompanyMemberProfile(): CompanyMemberDto? {
		return companyDao.findAll().firstOrNull()?.firstOrNull()?.toDto()
	}

	override suspend fun setCurrentStudentProfile(studentDto: StudentDto) {
		studentDao.upsert(studentDto.toDomain())
	}

	override suspend fun setCurrentCompanyMemberProfile(companyMemberDto: CompanyMemberDto) {
		companyDao.upsert(companyMemberDto.toDomain())
	}

	override suspend fun clear() {
		studentDao.clearAll()
		companyDao.clearAll()
	}
}