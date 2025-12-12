package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.InternshipJoined
import com.internconnect.internconnectbackendktor.model.raw.internship.Internship
import java.util.UUID

interface IInternshipService {
	suspend fun getAll(): List<InternshipJoined>
	suspend fun getById(id: UUID): InternshipJoined?
	suspend fun getByCompanyId(companyId: UUID): List<InternshipJoined>
	suspend fun getByCategory(category: String): List<InternshipJoined>
	suspend fun create(internship: Internship): InternshipJoined?
	suspend fun update(internship: Internship): InternshipJoined?
	suspend fun delete(id: UUID): Boolean
}