package com.internconnect.repository.specification

import com.internconnect.model.company.Company
import java.util.*

interface ICompanyRepository {
	suspend fun findAll(): List<Company>
	suspend fun findById(id: UUID): Company?
	suspend fun create(company: Company): Company?
	suspend fun update(company: Company): Company?
	suspend fun delete(id: UUID): Boolean
}