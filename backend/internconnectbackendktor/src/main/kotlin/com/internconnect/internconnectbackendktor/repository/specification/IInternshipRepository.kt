package com.internconnect.internconnectbackendktor.repository.specification

import com.internconnect.internconnectbackendktor.model.raw.internship.Internship
import java.util.UUID

interface IInternshipRepository {
	suspend fun findAll(): List<Internship>
	suspend fun findById(id: UUID): Internship?
	suspend fun findByCompanyId(companyId: UUID): List<Internship>
	suspend fun findByCategory(category: String): List<Internship>
	suspend fun create(internship: Internship): Internship?
	suspend fun update(internship: Internship): Internship?
	suspend fun delete(id: UUID): Boolean
}