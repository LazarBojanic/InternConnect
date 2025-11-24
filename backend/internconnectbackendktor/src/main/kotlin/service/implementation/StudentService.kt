package com.internconnect.service.implementation

import com.internconnect.model.student.Student
import com.internconnect.repository.specification.IStudentRepository
import com.internconnect.service.specification.IStudentService
import java.util.*

class StudentService(
	private val studentRepository: IStudentRepository,
) : IStudentService {
	override suspend fun getAll(): List<Student> {
		return studentRepository.findAll()
	}

	override suspend fun getById(id: UUID): Student? {
		return studentRepository.findById(id)
	}

	override suspend fun create(student: Student): Student? {
		return studentRepository.create(student)
	}

	override suspend fun update(student: Student): Student? {
		return studentRepository.update(student)
	}

	override suspend fun delete(id: UUID): Boolean {
		return studentRepository.delete(id)
	}
}