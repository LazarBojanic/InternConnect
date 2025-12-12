package com.internconnect.internconnectbackendktor.model.raw.companymember

import com.internconnect.internconnectbackendktor.model.raw.company.CompanyTable
import com.internconnect.internconnectbackendktor.model.raw.user.UserTable
import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant
import java.util.*

object CompanyMemberTable : IdTable<UUID>(name = "company_member") {
	override val id: Column<EntityID<UUID>> = reference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE).uniqueIndex()
	val userId = id
	val companyID = reference("company_id", CompanyTable.id)
	val role = enumeration("role", CompanyMemberRole::class)
	val status = enumeration("status", CompanyMemberStatus::class)
	val joinedAt = timestamp("joined_at").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}
