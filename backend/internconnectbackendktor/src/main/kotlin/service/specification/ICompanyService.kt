package com.internconnect.service.specification

import com.internconnect.model.company.Company
import java.util.UUID

interface ICompanyService {
	suspend fun getAll(): List<Company>
	suspend fun getById(id: UUID): Company?
	suspend fun create(company: Company): Company?
	suspend fun update(company: Company): Company?
	suspend fun delete(id: UUID): Boolean
}