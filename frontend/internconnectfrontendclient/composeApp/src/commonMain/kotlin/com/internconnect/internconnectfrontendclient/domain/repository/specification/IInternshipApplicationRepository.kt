package com.internconnect.internconnectfrontendclient.domain.repository.specification

import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplication

interface IInternshipApplicationRepository {
	suspend fun getById(id: String): InternshipApplication?
	suspend fun getAllByInternshipId(internshipId: String): List<InternshipApplication>
	suspend fun getAllByStudentId(studentId: String): List<InternshipApplication>
	suspend fun getAll(): List<InternshipApplication>
	suspend fun upsert(internship: InternshipApplication)
	suspend fun delete(internship: InternshipApplication)
	suspend fun clear()
}