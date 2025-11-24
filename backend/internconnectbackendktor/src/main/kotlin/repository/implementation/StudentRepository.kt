package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.setFrom
import com.internconnect.model.student.Student
import com.internconnect.model.student.StudentEntity
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IStudentRepository
import java.time.Instant
import java.util.*

class StudentRepository : IStudentRepository {
	override suspend fun findAll(): List<Student> {
		return dbQuery { StudentEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): Student? {
		return dbQuery { StudentEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(student: Student): Student? {
		return dbQuery {
			StudentEntity.new(student.userId) { setFrom(student, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(student: Student): Student? {
		return dbQuery {
			val e = StudentEntity.findById(student.userId) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(student.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = StudentEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}