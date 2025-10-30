package com.internconnect.model


import com.internconnect.model.auditlog.AuditLog
import com.internconnect.model.auditlog.AuditLogEntity
import com.internconnect.model.company.Company
import com.internconnect.model.company.CompanyEntity
import com.internconnect.model.company.CompanyTable
import com.internconnect.model.companyinvitation.CompanyInvitation
import com.internconnect.model.companyinvitation.CompanyInvitationEntity
import com.internconnect.model.companymember.CompanyMember
import com.internconnect.model.companymember.CompanyMemberEntity
import com.internconnect.model.companymember.CompanyMemberRole
import com.internconnect.model.companymember.CompanyMemberStatus
import com.internconnect.model.emailverification.EmailVerification
import com.internconnect.model.emailverification.EmailVerificationEntity
import com.internconnect.model.oauthaccount.OAuthAccount
import com.internconnect.model.oauthaccount.OAuthAccountEntity
import com.internconnect.model.passwordauth.PasswordAuth
import com.internconnect.model.passwordauth.PasswordAuthEntity
import com.internconnect.model.passwordreset.PasswordReset
import com.internconnect.model.passwordreset.PasswordResetEntity
import com.internconnect.model.refreshtoken.RefreshToken
import com.internconnect.model.refreshtoken.RefreshTokenEntity
import com.internconnect.model.student.Student
import com.internconnect.model.student.StudentEntity
import com.internconnect.model.user.*
import org.jetbrains.exposed.v1.core.dao.id.EntityID


fun AuditLogEntity.toDomain(): AuditLog = AuditLog(
	id = this.id.value,
	userId = this.userId.value,
	action = this.action,
	metadata = this.metadata,
	ip = this.ip,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun AuditLogEntity.setFrom(d: AuditLog, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	action = d.action
	metadata = d.metadata
	ip = d.ip
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun UserEntity.toDomain(): User = User(
	id = this.id.value,
	email = this.email,
	fullName = this.fullName ?: "",
	userRole = runCatching { UserRole.valueOf(this.role) }.getOrDefault(UserRole.STUDENT),
	isEmailVerified = this.isEmailVerified,
	userStatus = runCatching { this.status?.let { UserStatus.valueOf(it) } }.getOrNull() ?: UserStatus.ACTIVE,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun UserEntity.setFrom(d: User, mode: MapMode) {
	email = d.email
	fullName = d.fullName
	role = d.userRole.name
	isEmailVerified = d.isEmailVerified
	status = d.userStatus.name
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun CompanyEntity.toDomain(): Company = Company(
	id = this.id.value,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	hqCountry = this.hqCountry,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyEntity.setFrom(d: Company, mode: MapMode) {
	name = d.name
	industry = d.industry
	website = d.website
	logoUrl = d.logoUrl
	hqCountry = d.hqCountry
	city = d.city
	about = d.about
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun CompanyInvitationEntity.toDomain(): CompanyInvitation = CompanyInvitation(
	id = this.id.value,
	companyID = this.companyID.value,
	email = this.email,
	codeHash = this.codeHash,
	expiresAt = this.expiresAt,
	invitedBy = this.invitedBy.value,
	acceptedBy = this.acceptedBy?.value,
	acceptedAt = this.acceptedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyInvitationEntity.setFrom(d: CompanyInvitation, mode: MapMode) {
	companyID = EntityID(d.companyID, CompanyTable)
	email = d.email
	codeHash = d.codeHash
	expiresAt = d.expiresAt
	invitedBy = EntityID(d.invitedBy, UserTable)
	acceptedBy = d.acceptedBy?.let { EntityID(it, UserTable) }
	acceptedAt = d.acceptedAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun CompanyMemberEntity.toDomain(): CompanyMember = CompanyMember(
	id = this.id.value,
	companyId = this.companyID.value,
	userId = this.userId.value,
	companyMemberRole = runCatching { CompanyMemberRole.valueOf(this.companyMemberRole) }
		.getOrDefault(CompanyMemberRole.viewer),
	companyMemberStatus = runCatching { CompanyMemberStatus.valueOf(this.companyMemberStatus) }
		.getOrDefault(CompanyMemberStatus.active),
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyMemberEntity.setFrom(d: CompanyMember, mode: MapMode) {
	companyID = EntityID(d.companyId, CompanyTable)
	userId = EntityID(d.userId, UserTable)
	companyMemberRole = d.companyMemberRole.name
	companyMemberStatus = d.companyMemberStatus.name
	joinedAt = d.joinedAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun EmailVerificationEntity.toDomain(): EmailVerification = EmailVerification(
	id = this.id.value,
	userId = this.userId.value,
	codeHash = this.codeHash,
	sentToEmail = this.sentToEmail,
	expiresAt = this.expiresAt,
	consumedAt = this.consumedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun EmailVerificationEntity.setFrom(d: EmailVerification, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	codeHash = d.codeHash
	sentToEmail = d.sentToEmail
	expiresAt = d.expiresAt
	consumedAt = d.consumedAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

// -------------------- OAuthAccount --------------------
fun OAuthAccountEntity.toDomain(): OAuthAccount = OAuthAccount(
	id = this.id.value,
	userId = this.userId.value,
	provider = this.provider ?: "google",
	providerUserID = this.providerUserID,
	providerEmail = this.providerEmail,
	encryptedAccessToken = this.encryptedAccessToken,
	encryptedRefreshToken = this.encryptedRefreshToken,
	tokenExpiresAt = this.tokenExpiresAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun OAuthAccountEntity.setFrom(d: OAuthAccount, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	provider = d.provider
	providerUserID = d.providerUserID
	providerEmail = d.providerEmail
	encryptedAccessToken = d.encryptedAccessToken
	encryptedRefreshToken = d.encryptedRefreshToken
	tokenExpiresAt = d.tokenExpiresAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun PasswordAuthEntity.toDomain(): PasswordAuth = PasswordAuth(
	userId = this.id.value,
	encryptedPassword = this.encryptedPassword,
	encryptionAlgorithm = this.encryptionAlgorithm,
	passwordSetAt = this.passwordSetAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun PasswordAuthEntity.setFrom(d: PasswordAuth, mode: MapMode) {
	encryptedPassword = d.encryptedPassword
	encryptionAlgorithm = d.encryptionAlgorithm
	passwordSetAt = d.passwordSetAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun PasswordResetEntity.toDomain(): PasswordReset = PasswordReset(
	id = this.id.value,
	userId = this.userId.value,
	codeHash = this.codeHash,
	expiresAt = this.expiresAt,
	consumedAt = this.consumedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun PasswordResetEntity.setFrom(d: PasswordReset, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	codeHash = d.codeHash
	expiresAt = d.expiresAt
	consumedAt = d.consumedAt
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun RefreshTokenEntity.toDomain(): RefreshToken = RefreshToken(
	id = this.id.value,
	userId = this.userId.value,
	sessionId = this.sessionId,
	hash = this.hash,
	issuedAt = this.issuedAt,
	expiresAt = this.expiresAt,
	revokedAt = this.revokedAt,
	userAgent = this.userAgent,
	ip = this.ip,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun RefreshTokenEntity.setFrom(d: RefreshToken, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	sessionId = d.sessionId
	hash = d.hash
	issuedAt = d.issuedAt
	expiresAt = d.expiresAt
	revokedAt = d.revokedAt
	userAgent = d.userAgent
	ip = d.ip
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}

fun StudentEntity.toDomain(): Student = Student(
	userId = this.id.value,
	firstName = this.firstName,
	lastName = this.lastName,
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun StudentEntity.setFrom(d: Student, mode: MapMode) {
	firstName = d.firstName
	lastName = d.lastName
	schoolName = d.schoolName
	grade = d.grade
	bio = d.bio
	interests = d.interests
	avatarUrl = d.avatarUrl
	when (mode) {
		MapMode.Insert -> { createdAt = d.createdAt; updatedAt = d.updatedAt }
		MapMode.Update -> { updatedAt = d.updatedAt }
	}
}