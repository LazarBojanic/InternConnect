package com.internconnect.repository.implementation

import com.internconnect.database.dbQuery
import com.internconnect.model.MapMode
import com.internconnect.model.companymember.CompanyMember
import com.internconnect.model.companymember.CompanyMemberEntity
import com.internconnect.model.companymember.CompanyMemberTable
import com.internconnect.model.setFrom
import com.internconnect.model.toDomain
import com.internconnect.repository.specification.ICompanyMemberRepository
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Instant
import java.util.*

class   CompanyMemberRepository : ICompanyMemberRepository {
	override suspend fun findAll(): List<CompanyMember> {
		return dbQuery {
			CompanyMemberEntity.all().map { it.toDomain() }
		}
	}


	override suspend fun findByUserId(userId: UUID): CompanyMember? {
		return dbQuery {
			CompanyMemberEntity.find { CompanyMemberTable.userId eq userId }.firstOrNull()?.toDomain()
		}
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMember? {
		return dbQuery {
			CompanyMemberEntity.new(companyMember.userId) { setFrom(companyMember, MapMode.Insert) }.toDomain()
		}
	}

	override suspend fun update(companyMember: CompanyMember): CompanyMember? {
		return dbQuery {
			val e = CompanyMemberEntity.findById(companyMember.userId) ?: return@dbQuery null
			e.updatedAt = Instant.now()
			e.setFrom(companyMember.copy(updatedAt = e.updatedAt), MapMode.Update)
			e.toDomain()
		}
	}

	override suspend fun delete(id: UUID): Boolean {
		return dbQuery {
			val e = CompanyMemberEntity.findById(id) ?: return@dbQuery false
			e.delete(); true
		}
	}
}