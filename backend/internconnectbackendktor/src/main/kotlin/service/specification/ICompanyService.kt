package com.internconnect.service.specification

import com.internconnect.model.company.Company
import java.util.*

interface ICompanyService {
	suspend fun getAll(): List<Company>
	suspend fun getById(id: UUID): Company?
	suspend fun getByName(name: String): Company?
	suspend fun create(company: Company): Company?
	suspend fun update(company: Company): Company?
	suspend fun delete(id: UUID): Boolean
}