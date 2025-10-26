package com.internconnect.model.companyinvitation

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.UUIDEntity
import org.jetbrains.exposed.v1.dao.UUIDEntityClass
import java.util.*

class CompanyInvitationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<CompanyInvitationEntity>(CompanyInvitationTable)
	var companyID by CompanyInvitationTable.companyID
	var email by CompanyInvitationTable.email
	var codeHash by CompanyInvitationTable.codeHash
	var expiresAt by CompanyInvitationTable.expiresAt
	var invitedBy by CompanyInvitationTable.invitedBy
	var acceptedBy by CompanyInvitationTable.acceptedBy
	var acceptedAt by CompanyInvitationTable.acceptedAt
	var createdAt by CompanyInvitationTable.createdAt
	var updatedAt by CompanyInvitationTable.updatedAt
}

