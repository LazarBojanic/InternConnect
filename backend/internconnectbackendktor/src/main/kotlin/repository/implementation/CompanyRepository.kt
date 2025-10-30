package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.company.Company
import com.internconnect.model.company.CompanyEntity
import com.internconnect.model.company.CompanyTable
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyRepository
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
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