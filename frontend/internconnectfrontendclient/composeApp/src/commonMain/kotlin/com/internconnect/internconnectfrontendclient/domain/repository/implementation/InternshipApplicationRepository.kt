package com.internconnect.internconnectfrontendclient.domain.repository.implementation

import com.internconnect.internconnectfrontendclient.data.model.dao.InternshipApplicationDao
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplication
import com.internconnect.internconnectfrontendclient.domain.repository.specification.IInternshipApplicationRepository
import kotlinx.coroutines.flow.firstOrNull

class InternshipApplicationRepository(
	private val internshipApplicationDao: InternshipApplicationDao
) : IInternshipApplicationRepository{
	override suspend fun getById(id: String): InternshipApplication? {
		return internshipApplicationDao.findById(id).firstOrNull()
	}

	override suspend fun getAllByInternshipId(internshipId: String): List<InternshipApplication> {
		return internshipApplicationDao.findAllByInternshipId(internshipId).firstOrNull() ?: listOf()
	}

	override suspend fun getAllByStudentId(studentId: String): List<InternshipApplication> {
		return internshipApplicationDao.findAllByStudentId(studentId).firstOrNull() ?: listOf()
	}

	override suspend fun getAll(): List<InternshipApplication> {
		return internshipApplicationDao.findAll().firstOrNull() ?: listOf()
	}

	override suspend fun upsert(internship: InternshipApplication) {
		internshipApplicationDao.upsert(internship)
	}

	override suspend fun delete(internship: InternshipApplication) {
		internshipApplicationDao.delete(internship)
	}

	override suspend fun clear() {
		internshipApplicationDao.clearAll()
	}
}