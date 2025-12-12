package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.company.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyRepository
import org.jetbrains.exposed.v1.core.eq
import java.time.Instant
import java.util.*

class CompanyRepository : ICompanyRepository {
	override suspend fun findAll(): List<Company> {
		return dbQuery {
			CompanyEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): Company? {
		return dbQuery {
			CompanyEntity.findById(id)?.toDomain()
		}
	}

	override suspend fun findByName(name: String): Company? {
		return dbQuery {
			CompanyEntity.find { CompanyTable.name eq name }.firstOrNull()?.toDomain()
		}
	}

	override suspend fun create(company: Company): Company? {
		return dbQuery {
			CompanyEntity.new(company.id) { setFrom(company, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(company: Company): Company? {
		return dbQuery {
			val e = CompanyEntity.findById(company.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(company.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = CompanyEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}