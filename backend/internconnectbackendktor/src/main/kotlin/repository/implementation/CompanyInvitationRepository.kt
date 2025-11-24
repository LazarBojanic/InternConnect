package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.model.companyinvitation.CompanyInvitationEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyInvitationRepository
import java.time.Instant
import java.util.*

class CompanyInvitationRepository : ICompanyInvitationRepository {
	override suspend fun findAll(): List<CompanyInvitation> {
		return dbQuery { CompanyInvitationEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): CompanyInvitation? {
		return dbQuery { CompanyInvitationEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return dbQuery {
			CompanyInvitationEntity.new(companyInvitation.id) { setFrom(companyInvitation, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return dbQuery {
			val e = CompanyInvitationEntity.findById(companyInvitation.id) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(companyInvitation.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = CompanyInvitationEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}