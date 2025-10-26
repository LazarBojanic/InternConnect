package com.internconnect.repository.implementation

import com.internconnect.model.studentprofile.Student
import com.internconnect.model.user.User
import com.internconnect.model.user.UserEntity
import com.internconnect.model.user.UserTable
import com.internconnect.repository.specification.IStudentRepository
import com.internconnect.repository.specification.IUserRepository
import org.jetbrains.exposed.v1.core.eq
import java.util.UUID

class StudentRepository : IStudentRepository {
	override suspend fun findAll(): List<Student> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): Student? {
		TODO("Not yet implemented")
	}

	override suspend fun create(student: Student): Student? {
		TODO("Not yet implemented")
	}

	override suspend fun update(student: Student): Student? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}