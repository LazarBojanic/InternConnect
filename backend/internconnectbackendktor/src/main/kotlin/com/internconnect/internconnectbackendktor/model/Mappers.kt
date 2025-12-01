package com.internconnect.internconnectbackendktor.model

import com.internconnect.internconnectbackendktor.model.raw.auditlog.*
import com.internconnect.internconnectbackendktor.model.raw.company.*
import com.internconnect.internconnectbackendktor.model.raw.companymember.*
import com.internconnect.internconnectbackendktor.model.raw.emailverification.*
import com.internconnect.internconnectbackendktor.model.raw.internship.*
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.*
import com.internconnect.internconnectbackendktor.model.raw.oauthaccount.*
import com.internconnect.internconnectbackendktor.model.raw.passwordauth.*
import com.internconnect.internconnectbackendktor.model.raw.passwordreset.*
import com.internconnect.internconnectbackendktor.model.raw.refreshtoken.*
import com.internconnect.internconnectbackendktor.model.raw.student.*
import com.internconnect.internconnectbackendktor.model.raw.user.*

import com.internconnect.internconnectbackendktor.model.dto.response.*

import com.internconnect.internconnectbackendktor.model.joined.*
import com.internconnect.internconnectbackendktor.model.raw.session.Session
import com.internconnect.internconnectbackendktor.model.raw.session.SessionEntity
import com.internconnect.internconnectbackendktor.model.raw.session.SessionTable

import org.jetbrains.exposed.v1.core.dao.id.EntityID

enum class MapMode { Insert, Update }

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
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}

fun AuditLog.join(user: UserJoined): AuditLogJoined = AuditLogJoined(
	id = this.id,
	user = user,
	action = this.action,
	metadata = this.metadata,
	ip = this.ip,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)


fun UserEntity.toDomain(): User = User(
	id = this.id.value,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun UserEntity.setFrom(d: User, mode: MapMode) {
	email = d.email
	firstName = d.firstName
	lastName = d.lastName
	role = d.role
	isEmailVerified = d.isEmailVerified
	status = d.status
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}

fun User.join(): UserJoined = UserJoined(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun UserJoined.toDto(): UserDto = UserDto(
	id = this.id.toString(),
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role.name,
	isEmailVerified = this.isEmailVerified,
	status = this.status.name,
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),
)


fun CompanyEntity.toDomain(): Company = Company(
	id = this.id.value,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
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
	country = d.country
	city = d.city
	about = d.about
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}

fun Company.join(): CompanyJoined = CompanyJoined(
	id = this.id,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyJoined.toDto(): CompanyDto = CompanyDto(
	id = this.id.toString(),
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),

)

fun CompanyMemberEntity.toDomain(): CompanyMember = CompanyMember(
	userId = this.userId.value,
	companyId = this.companyID.value,
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyMemberEntity.setFrom(d: CompanyMember, mode: MapMode) {
	companyID = EntityID(d.companyId, CompanyTable)
	userId = EntityID(d.userId, UserTable)
	role = d.role
	status = d.status
	joinedAt = d.joinedAt
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun CompanyMember.join(user: UserJoined, company: CompanyJoined): CompanyMemberJoined = CompanyMemberJoined(
	user = user,
	company = company,
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun CompanyMemberJoined.toDto(): CompanyMemberDto = CompanyMemberDto(
	user = this.user.toDto(),
	company = this.company.toDto(),
	role = this.role.name,
	status = this.status.name,
	joinedAt = this.joinedAt.toString(),
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),

)

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
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun EmailVerification.join(user: UserJoined): EmailVerificationJoined = EmailVerificationJoined(
	id = this.id,
	user = user,
	codeHash = this.codeHash,
	sentToEmail = this.sentToEmail,
	expiresAt = this.expiresAt,
	consumedAt = this.consumedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)


fun InternshipEntity.toDomain(): Internship = Internship(
	id = this.id.value,
	companyId = this.companyId.value,
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun InternshipEntity.setFrom(d: Internship, mode: MapMode) {
	companyId = EntityID(d.companyId, CompanyTable)
	title = d.title
	category = d.category
	description = d.description
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}
		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun Internship.join(company: CompanyJoined): InternshipJoined = InternshipJoined(
	id = this.id,
	company = company,
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,

)

fun InternshipJoined.toDto(): InternshipDto = InternshipDto(
	id = this.id.toString(),
	company = this.company.toDto(),
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),


)

fun InternshipApplicationEntity.toDomain(): InternshipApplication = InternshipApplication(
	id = this.id.value,
	internshipId = this.internshipId.value,
	studentId = this.studentId.value,
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)
fun InternshipApplicationEntity.setFrom(d: InternshipApplication, mode: MapMode) {
	internshipId = EntityID(d.internshipId, InternshipTable)
	studentId = EntityID(d.studentId, StudentTable)
	resume = d.resume
	interviewNotes = d.interviewNotes
	status = d.status
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}
		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}

fun InternshipApplication.join(internship: InternshipJoined, student: StudentJoined): InternshipApplicationJoined = InternshipApplicationJoined(
	id = this.id,
	internship = internship,
	student = student,
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun InternshipApplicationJoined.toDto(): InternshipApplicationDto = InternshipApplicationDto(
	id = this.id.toString(),
	internship = this.internship.toDto(),
	student = this.student.toDto(),
	status = this.status.name,
	resume = this.resume.toString(),
	interviewNotes = this.interviewNotes,
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),

)

fun OAuthAccountEntity.toDomain(): OAuthAccount = OAuthAccount(
	id = this.id.value,
	userId = this.userId.value,
	provider = this.provider ?: "google",
	providerUserId = this.providerUserId,
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
	providerUserId = d.providerUserId
	providerEmail = d.providerEmail
	encryptedAccessToken = d.encryptedAccessToken
	encryptedRefreshToken = d.encryptedRefreshToken
	tokenExpiresAt = d.tokenExpiresAt
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun OAuthAccount.join(user: UserJoined): OAuthAccountJoined = OAuthAccountJoined(
	id = this.id,
	user = user,
	provider = this.provider,
	providerUserId = this.providerUserId,
	providerEmail = this.providerEmail,
	encryptedAccessToken = this.encryptedAccessToken,
	encryptedRefreshToken = this.encryptedRefreshToken,
	tokenExpiresAt = this.tokenExpiresAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)


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
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun PasswordAuth.join(user: UserJoined): PasswordAuthJoined = PasswordAuthJoined(
	user = user,
	encryptedPassword = this.encryptedPassword,
	encryptionAlgorithm = this.encryptionAlgorithm,
	passwordSetAt = this.passwordSetAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

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
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}

fun PasswordReset.join(user: UserJoined): PasswordResetJoined = PasswordResetJoined(
	id = this.id,
	user = user,
	codeHash = this.codeHash,
	expiresAt = this.expiresAt,
	consumedAt = this.consumedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,


)

fun RefreshTokenEntity.toDomain(): RefreshToken = RefreshToken(
	id = this.id.value,
	sessionId = this.sessionId.value,
	hash = this.hash,
	issuedAt = this.issuedAt,
	expiresAt = this.expiresAt,
	revokedAt = this.revokedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun RefreshTokenEntity.setFrom(d: RefreshToken, mode: MapMode) {
	sessionId = EntityID(d.sessionId, SessionTable)
	hash = d.hash
	issuedAt = d.issuedAt
	expiresAt = d.expiresAt
	revokedAt = d.revokedAt
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}
		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}

}
fun RefreshToken.join(sessionJoined: SessionJoined): RefreshTokenJoined = RefreshTokenJoined(
	id = this.id,
	sessionJoined = sessionJoined,
	hash = this.hash,
	issuedAt = this.issuedAt,
	expiresAt = this.expiresAt,
	revokedAt = this.revokedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun StudentEntity.toDomain(): Student = Student(
	userId = this.id.value,
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun StudentEntity.setFrom(d: Student, mode: MapMode) {
	schoolName = d.schoolName
	grade = d.grade
	bio = d.bio
	interests = d.interests
	avatarUrl = d.avatarUrl
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}

		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun Student.join(user: UserJoined): StudentJoined = StudentJoined(
	user = user,
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,

)

fun StudentJoined.toDto(): StudentDto = StudentDto(
	user = this.user.toDto(),
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt.toString(),
	updatedAt = this.updatedAt.toString(),

)

fun SessionEntity.toDomain(): Session = Session(
	id = this.id.value,
	userId = this.userId.value,
	ip = this.ip,
	userAgent = this.userAgent,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)

fun SessionEntity.setFrom(d: Session, mode: MapMode) {
	userId = EntityID(d.userId, UserTable)
	ip = d.ip
	userAgent = d.userAgent
	when (mode) {
		MapMode.Insert -> {
			createdAt = d.createdAt; updatedAt = d.updatedAt
		}
		MapMode.Update -> {
			updatedAt = d.updatedAt
		}
	}
}
fun Session.join(user: UserJoined): SessionJoined = SessionJoined(
	id = this.id,
	user = user,
	ip = this.ip,
	userAgent = this.userAgent,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
)
