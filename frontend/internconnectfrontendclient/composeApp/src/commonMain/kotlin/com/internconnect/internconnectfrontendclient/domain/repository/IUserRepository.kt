package com.internconnect.internconnectfrontendclient.domain.repository

import com.internconnect.internconnectfrontendclient.data.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.dto.response.StudentDto

interface IUserRepository {
	suspend fun getCurrentStudentProfile(): StudentDto?
	suspend fun getCurrentCompanyMemberProfile(): CompanyMemberDto?
	suspend fun setCurrentStudentProfile(studentDto: StudentDto)
	suspend fun setCurrentCompanyMemberProfile(companyMemberDto: CompanyMemberDto)
	suspend fun clear()
}

