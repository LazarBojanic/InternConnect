package com.internconnect.internconnectbackendktor.repository.implementation

import com.internconnect.internconnectbackendktor.database.dbQuery
import com.internconnect.internconnectbackendktor.model.MapMode
import com.internconnect.internconnectbackendktor.model.raw.companymember.*
import com.internconnect.internconnectbackendktor.model.setFrom
import com.internconnect.internconnectbackendktor.model.toDomain
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyMemberRepository
import org.jetbrains.exposed.v1.core.eq
import java.time.Instant
import java.util.*

class CompanyMemberRepository : ICompanyMemberRepository {
	override suspend fun findAll(): List<CompanyMember> {
		return dbQuery {
			CompanyMemberEntity.all().map { it.toDomain() }
		}
	}

	override suspend fun findById(id: UUID): CompanyMember? {
		return dbQuery { CompanyMemberEntity.findById(id)?.toDomain() }
	}

	override suspend fun create(companyMember: CompanyMember): CompanyMember? {
		return dbQuery {
			CompanyMemberEntity.new(companyMember.userId) { setFrom(companyMember, MapMode.Insert) }
				.toDomain()
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