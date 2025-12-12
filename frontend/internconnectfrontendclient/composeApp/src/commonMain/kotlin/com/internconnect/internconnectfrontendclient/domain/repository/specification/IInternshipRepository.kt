package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship

interface IInternshipRepository {
	suspend fun getById(id: String): Internship?
	suspend fun getAllByCompanyId(companyId: String): List<Internship>
	suspend fun getAll(): List<Internship>
	suspend fun upsert(internship: Internship)
	suspend fun delete(internship: Internship)
	suspend fun clear()
}