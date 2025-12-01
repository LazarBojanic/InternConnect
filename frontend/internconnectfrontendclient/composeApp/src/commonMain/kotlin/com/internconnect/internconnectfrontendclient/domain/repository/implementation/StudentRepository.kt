package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.StudentDao
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IStudentRepository
import kotlinx.coroutines.flow.first

class StudentRepository (
	private val studentDao: StudentDao
): IStudentRepository {
	override suspend fun getCurrentStudent(): Student? {
		return studentDao.findFirst().first()
	}

	override suspend fun setCurrentStudent(student: Student) {
		studentDao.upsert(student)
	}

	override suspend fun clear() {
		studentDao.clearAll()
	}
}