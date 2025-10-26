package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.model.companyinvitation.CompanyInvitationEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyInvitationRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class CompanyInvitationRepository : ICompanyInvitationRepository {
	override suspend fun findAll(): List<CompanyInvitation> {
		return transaction { CompanyInvitationEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): CompanyInvitation? {
		return transaction { CompanyInvitationEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return transaction {
			CompanyInvitationEntity.new(companyInvitation.id) { setFrom(companyInvitation, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(companyInvitation: CompanyInvitation): CompanyInvitation? {
		return transaction {
			val e = CompanyInvitationEntity.findById(companyInvitation.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(companyInvitation.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = CompanyInvitationEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}