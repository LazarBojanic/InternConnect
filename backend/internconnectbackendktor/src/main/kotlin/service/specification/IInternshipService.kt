package com.internconnect.service.specification

import com.internconnect.model.company.Company
import com.internconnect.model.internship.Internship
import java.util.UUID

interface IInternshipService {
	suspend fun getAll(): List<Internship>
	suspend fun getById(id: UUID): Internship?
	suspend fun getByCompanyId(companyId: UUID): List<Internship>
	suspend fun getByCategory(category: String): List<Internship>
	suspend fun create(company: Company): Internship?
	suspend fun update(company: Company): Internship?
	suspend fun delete(id: UUID): Boolean
}