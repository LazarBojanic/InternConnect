package com.internconnect.service.specification

import com.internconnect.model.student.Student
import java.util.*

interface IStudentService {
	suspend fun getAll(): List<Student>
	suspend fun getById(id: UUID): Student?
	suspend fun create(student: Student): Student?
	suspend fun update(student: Student): Student?
	suspend fun delete(id: UUID): Boolean
}