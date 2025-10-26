package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.setFrom
import com.internconnect.model.student.Student
import com.internconnect.model.student.StudentEntity
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IStudentRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class StudentRepository : IStudentRepository {
	override suspend fun findAll(): List<Student> {
		return transaction { StudentEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): Student? {
		return transaction { StudentEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(student: Student): Student? {
		return transaction {
			StudentEntity.new(student.userId) { setFrom(student, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(student: Student): Student? {
		return transaction {
			val e = StudentEntity.findById(student.userId) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(student.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = StudentEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}