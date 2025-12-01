package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.InternshipDao
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IInternshipRepository
import kotlinx.coroutines.flow.firstOrNull

class InternshipRepository (
	private val internshipDao: InternshipDao
) : IInternshipRepository{
	override suspend fun getById(id: String): Internship? {
		return internshipDao.findById(id).firstOrNull()
	}

	override suspend fun getAllByCompanyId(companyId: String): List<Internship> {
		return internshipDao.findAllByCompanyId(companyId).firstOrNull() ?: listOf()
	}

	override suspend fun getAll(): List<Internship> {
		return internshipDao.findAll().firstOrNull() ?: listOf()
	}

	override suspend fun upsert(internship: Internship) {
		internshipDao.upsert(internship)
	}

	override suspend fun delete(internship: Internship) {
		internshipDao.delete(internship)
	}

	override suspend fun clear() {
		internshipDao.clearAll()
	}
}