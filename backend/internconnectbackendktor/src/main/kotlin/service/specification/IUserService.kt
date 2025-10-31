package com.internconnect.service.specification

import com.internconnect.dto.CompanyMemberProfileDto
import com.internconnect.dto.StudentProfileDto
import com.internconnect.model.user.User
import java.util.*

interface IUserService {
	suspend fun getAll(): List<User>
	suspend fun getById(id: UUID): User?
	suspend fun getByEmail(email: String): User?
	suspend fun getStudentProfileById(userId: UUID): StudentProfileDto?
	suspend fun getCompanyMemberProfileByUserId(userId: UUID): CompanyMemberProfileDto?
	suspend fun create(user: User): User?
	suspend fun update(user: User): User?
	suspend fun delete(id: UUID): Boolean
}