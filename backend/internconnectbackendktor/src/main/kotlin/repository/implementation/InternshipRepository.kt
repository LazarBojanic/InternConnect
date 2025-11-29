package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import com.internconnect.model.internship.InternshipEntity
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.IInternshipRepository
import kotlinx.html.Entities
import java.util.UUID

class InternshipRepository : IInternshipRepository {
	override suspend fun findAll(): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun findById(id: UUID): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun findByCompanyId(companyId: UUID): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun findByCategory(category: String): List<Internship> {
		TODO("Not yet implemented")
	}

	override suspend fun create(internship: Internship): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun update(internship: Internship): Internship? {
		TODO("Not yet implemented")
	}

	override suspend fun delete(id: UUID): Boolean {
		TODO("Not yet implemented")
	}
}