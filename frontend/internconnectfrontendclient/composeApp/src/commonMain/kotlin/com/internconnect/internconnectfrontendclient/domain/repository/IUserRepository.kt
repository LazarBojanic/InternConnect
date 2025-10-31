package com.internconnect.internconnectfrontendclient.domain.repository

import com.internconnect.internconnectfrontendclient.data.dto.CompanyMemberProfileDto
import com.internconnect.internconnectfrontendclient.data.dto.StudentProfileDto

interface IUserRepository {
	suspend fun getCurrentStudentProfile(): StudentProfileDto?
	suspend fun getCurrentCompanyMemberProfile(): CompanyMemberProfileDto?
	suspend fun setCurrentStudentProfile(studentProfileDto: StudentProfileDto)
	suspend fun setCurrentCompanyMemberProfile(companyMemberProfileDto: CompanyMemberProfileDto)
	suspend fun clear()
}

