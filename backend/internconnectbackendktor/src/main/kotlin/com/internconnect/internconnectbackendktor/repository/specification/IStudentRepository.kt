package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.student.Student
import java.util.*

interface IStudentRepository {
	suspend fun findAll(): List<Student>
	suspend fun findById(id: UUID): Student?
	suspend fun create(student: Student): Student?
	suspend fun update(student: Student): Student?
	suspend fun delete(id: UUID): Boolean
}