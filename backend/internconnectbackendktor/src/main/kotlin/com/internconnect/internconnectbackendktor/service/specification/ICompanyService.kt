package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.CompanyJoined
import com.internconnect.internconnectbackendktor.model.raw.company.Company
import java.util.*

interface ICompanyService {
	suspend fun getAll(): List<CompanyJoined>
	suspend fun getById(id: UUID): CompanyJoined?
	suspend fun getByName(name: String): CompanyJoined?
	suspend fun create(company: Company): CompanyJoined?
	suspend fun update(company: Company): CompanyJoined?
	suspend fun delete(id: UUID): Boolean
}