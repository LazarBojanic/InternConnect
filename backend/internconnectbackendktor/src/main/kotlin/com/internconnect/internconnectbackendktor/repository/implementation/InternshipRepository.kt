package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.internship.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.IInternshipRepository
import org.jetbrains.exposed.v1.core.eq
import java.time.Instant
import java.util.UUID

class InternshipRepository : IInternshipRepository {
	override suspend fun findAll(): List<Internship> {
		return dbQuery { InternshipEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): Internship? {
		return dbQuery { InternshipEntity.findById(id)?.toDomain() }
	}

	override suspend fun findByCompanyId(companyId: UUID): List<Internship> {
		return dbQuery {
			InternshipEntity.find { InternshipTable.companyId eq companyId }.map { it.toDomain() }
		}
	}

	override suspend fun findByCategory(category: String): List<Internship> {
		return dbQuery {
			InternshipEntity.find { InternshipTable.category eq category }.map { it.toDomain() }
		}
	}

	override suspend fun create(internship: Internship): Internship? {
		return dbQuery {
			InternshipEntity.new(internship.id) { setFrom(internship, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(internship: Internship): Internship? {
		return dbQuery {
			val e = InternshipEntity.findById(internship.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(internship.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = InternshipEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}