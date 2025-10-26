package com.internconnect.service.implementation

import com.internconnect.model.studentprofile.Student
import com.internconnect.model.user.User
import com.internconnect.repository.implementation.UserRepository
import com.internconnect.repository.specification.IStudentRepository
import com.internconnect.service.specification.IStudentService
import com.internconnect.service.specification.IUserService
import java.util.UUID

class StudentService (
	private val studentRepository: IStudentRepository,
) : IStudentService {
	override suspend fun getAll(): List<Student> {
		TODO("Not yet implemented")
	}

	override suspend fun getById(id: UUID): Student? {
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