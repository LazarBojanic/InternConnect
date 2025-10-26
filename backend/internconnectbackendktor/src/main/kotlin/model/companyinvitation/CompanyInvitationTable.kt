package com.internconnect.model.companyinvitation

import com.internconnect.model.company.CompanyTable
import com.internconnect.model.user.UserTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object CompanyInvitationTable : UUIDTable(name = "company_invitation") {
	val companyID = reference("companyID", CompanyTable.id)
	val email = varchar("email", length = 255)
	val codeHash = varchar("code_hash", length = 255)
	val expiresAt = varchar("expires_at", length = 255)
	val invitedBy = reference("invited_by", UserTable.id)
	val acceptedBy = reference("accepted_by", UserTable.id)
	val acceptedAt = timestamp("acceptedAt").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}

