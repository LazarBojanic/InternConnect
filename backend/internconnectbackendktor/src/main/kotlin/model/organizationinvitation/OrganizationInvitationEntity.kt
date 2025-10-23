package com.internconnect.model.organizationinvitation

import com.internconnect.model.organization.OrganizationTable
import com.internconnect.util.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.UUID

class OrganizationInvitationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<OrganizationInvitationEntity>(OrganizationInvitationTable)
	var organizationID by OrganizationInvitationTable.organizationID
	var codeHash by OrganizationInvitationTable.codeHash
	var expiresAt by OrganizationInvitationTable.expiresAt
	var invitedBy by OrganizationInvitationTable.invitedBy
	var acceptedBy by OrganizationInvitationTable.acceptedBy
	var acceptedAt by OrganizationInvitationTable.acceptedAt
	var createdAt by OrganizationInvitationTable.createdAt
	var updatedAt by OrganizationInvitationTable.updatedAt
}

