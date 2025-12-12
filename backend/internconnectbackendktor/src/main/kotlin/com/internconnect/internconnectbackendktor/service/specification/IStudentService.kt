package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.StudentJoined
import com.internconnect.internconnectbackendktor.model.raw.student.Student
import java.util.*

interface IStudentService {
	suspend fun getAll(): List<StudentJoined>
	suspend fun getById(id: UUID): StudentJoined?
	suspend fun create(student: Student): StudentJoined?
	suspend fun update(student: Student): StudentJoined?
	suspend fun delete(id: UUID): Boolean
}