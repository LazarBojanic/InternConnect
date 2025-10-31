package com.internconnect.internconnectfrontendclient.domain.repository

import com.internconnect.internconnectfrontendclient.data.dao.CompanyMemberProfileDao
import com.internconnect.internconnectfrontendclient.data.dao.StudentProfileDao
import com.internconnect.internconnectfrontendclient.data.database.AppDatabase
import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto
import com.internconnect.internconnectfrontendclient.data.toDomain
import com.internconnect.internconnectfrontendclient.data.toDto
import kotlinx.coroutines.flow.firstOrNull

class UserRepository(
	private val studentDao: StudentProfileDao,
	private val companyDao: CompanyMemberProfileDao,
) : IUserRepository {

	override suspend fun getCurrentStudentProfile(): StudentProfileDto? {

		return studentDao.findAll().firstOrNull()?.firstOrNull()?.toDto()
	}

	override suspend fun getCurrentCompanyMemberProfile(): CompanyMemberProfileDto? {
		return companyDao.findAll().firstOrNull()?.firstOrNull()?.toDto()
	}

	override suspend fun setCurrentStudentProfile(studentProfileDto: StudentProfileDto) {
		studentDao.upsert(studentProfileDto.toDomain())
	}

	override suspend fun setCurrentCompanyMemberProfile(companyMemberProfileDto: CompanyMemberProfileDto) {
		companyDao.upsert(companyMemberProfileDto.toDomain())
	}

	override suspend fun clear() {
		studentDao.clearAll()
		companyDao.clearAll()
	}
}