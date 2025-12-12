package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IInternshipApplicationRepository
import org.jetbrains.exposed.v1.core.eq
import java.time.Instant
import java.util.UUID

class InternshipApplicationRepository : IInternshipApplicationRepository {
	override suspend fun findAll(): List<InternshipApplication> {
		return dbQuery { InternshipApplicationEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): InternshipApplication? {
		return dbQuery { InternshipApplicationEntity.findById(id)?.toDomain() }
	}

	override suspend fun findByInternshipId(internshipId: UUID): List<InternshipApplication> {
		return dbQuery {
			InternshipApplicationEntity.find { InternshipApplicationTable.internshipId eq internshipId }
				.map { it.toDomain() }
		}
	}

	override suspend fun findByStudentId(studentId: UUID): List<InternshipApplication> {
		return dbQuery {
			InternshipApplicationEntity.find { InternshipApplicationTable.studentId eq studentId }
				.map { it.toDomain() }
		}
	}

	override suspend fun findByStatus(status: InternshipApplicationStatus): List<InternshipApplication> {
		return dbQuery {
			InternshipApplicationEntity.find { InternshipApplicationTable.status eq status }
				.map { it.toDomain() }
		}
	}

	override suspend fun create(internshipApplication: InternshipApplication): InternshipApplication? {
		return dbQuery {
			InternshipApplicationEntity.new(internshipApplication.id) {
				setFrom(
					internshipApplication,
					MapMode.Insert
				)
			}.toDomain()
		}
	}

	override suspend fun update(internshipApplication: InternshipApplication): InternshipApplication? {
		return dbQuery {
			val e = InternshipApplicationEntity.findById(internshipApplication.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(internshipApplication.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = InternshipApplicationEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}