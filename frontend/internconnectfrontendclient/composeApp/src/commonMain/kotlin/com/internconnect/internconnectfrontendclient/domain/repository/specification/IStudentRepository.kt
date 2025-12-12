package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.Student

interface IStudentRepository {
	suspend fun getCurrentStudent(): Student?
	suspend fun setCurrentStudent(student: Student)
	suspend fun clear()
}