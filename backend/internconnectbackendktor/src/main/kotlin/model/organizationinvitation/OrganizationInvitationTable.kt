package com.internconnect.model.organizationinvitation

import com.internconnect.model.organization.OrganizationTable
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object OrganizationInvitationTable : UUIDTable(name = "organization_invitation") {
	val organizationID = reference("organizationID", OrganizationTable.id)
	val email = varchar("email", length = 255)
	val codeHash = varchar("code_hash", length = 255)
	val expiresAt = varchar("expires_at", length = 255)
	val invitedBy = varchar("invited_by", length = 255)
	val acceptedBy = varchar("accepted_by", length = 255).nullable()
	val acceptedAt = datetime("acceptedAt").defaultExpression(CurrentDateTime).nullable()
	val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
	val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}

