package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.company.Company
import com.internconnect.model.company.CompanyEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class CompanyRepository : ICompanyRepository {
	override suspend fun findAll(): List<Company> {
		return transaction { CompanyEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): Company? {
		return transaction { CompanyEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(company: Company): Company? {
		return transaction {
			CompanyEntity.new(company.id) { setFrom(company, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(company: Company): Company? {
		return transaction {
			val e = CompanyEntity.findById(company.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(company.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = CompanyEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}