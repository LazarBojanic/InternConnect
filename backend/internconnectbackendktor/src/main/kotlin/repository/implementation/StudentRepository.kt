package com.internconnect.repository.implementation

import com.internconnect.model.student.Student
import com.internconnect.repository.specification.IStudentRepository
import java.util.*

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