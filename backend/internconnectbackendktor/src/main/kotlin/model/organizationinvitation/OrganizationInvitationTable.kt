package com.internconnect.model.organizationinvitation

import com.internconnect.model.organization.OrganizationTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp
import java.time.Instant

object OrganizationInvitationTable : UUIDTable(name = "organization_invitation") {
	val organizationID = reference("organizationID", OrganizationTable.id)
	val email = varchar("email", length = 255)
	val codeHash = varchar("code_hash", length = 255)
	val expiresAt = varchar("expires_at", length = 255)
	val invitedBy = varchar("invited_by", length = 255)
	val acceptedBy = varchar("accepted_by", length = 255).nullable()
	val acceptedAt = timestamp("acceptedAt").nullable()
	val createdAt = timestamp("created_at").default(Instant.now())
	val updatedAt = timestamp("updated_at").default(Instant.now())
}

