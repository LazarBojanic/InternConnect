package com.internconnect.repository.implementation

import com.internconnect.model.MapMode
import com.internconnect.model.companymember.CompanyMember
import com.internconnect.model.companymember.CompanyMemberEntity
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyMemberRepository
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class   CompanyMemberRepository : ICompanyMemberRepository {
	override suspend fun findAll(): List<CompanyMember> {
		return transaction { CompanyMemberEntity.all().map { it.toDomain() } }
	}

	override suspend fun findById(id: UUID): CompanyMember? {
		return transaction { CompanyMemberEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMember? {
		return transaction {
			CompanyMemberEntity.new(companyMember.id) { setFrom(companyMember, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(companyMember: CompanyMember): CompanyMember? {
		return transaction {
			val e = CompanyMemberEntity.findById(companyMember.id) ?: return@transaction null
			e.updatedAt = Instant.now()
			e.setFrom(companyMember.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return transaction {
			val e = CompanyMemberEntity.findById(id) ?: return@transaction false
			e.delete(); true
		}
	}
}