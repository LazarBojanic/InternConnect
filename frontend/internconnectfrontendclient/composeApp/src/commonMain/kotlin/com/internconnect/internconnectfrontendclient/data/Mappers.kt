package com.internconnect.internconnectfrontendclient.data

import com.internconnect.internconnectfrontendclient.data.model.dto.response.CompanyDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.CompanyMemberDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.InternshipApplicationDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.InternshipDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.StudentDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.UserDto
import com.internconnect.internconnectfrontendclient.data.model.joined.CompanyJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.CompanyMemberJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.StudentJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.UserJoined
import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplication
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import com.internconnect.internconnectfrontendclient.data.model.raw.User

fun User.join(): UserJoined = UserJoined(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun UserJoined.toRaw(): User = User(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun UserJoined.toDto(): UserDto = UserDto(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun UserDto.toRaw(): User = User(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun UserDto.toJoined(): UserJoined = UserJoined(
	id = this.id,
	email = this.email,
	firstName = this.firstName,
	lastName = this.lastName,
	role = this.role,
	isEmailVerified = this.isEmailVerified,
	status = this.status,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun Student.join(user: UserJoined): StudentJoined = StudentJoined(
	user = user,
	schoolName = schoolName,
	grade = grade,
	bio = bio,
	interests = interests,
	avatarUrl = avatarUrl,
	createdAt = createdAt,
	updatedAt = updatedAt
)
fun StudentJoined.toRaw(): Student = Student(
	userId = this.user.id,
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun StudentJoined.toDto(): StudentDto = StudentDto(
	user = this.user.toDto(),
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun StudentDto.toRaw(): Student = Student(
	userId = this.user.id,
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun StudentDto.toJoined(): StudentJoined = StudentJoined(
	user = this.user.toJoined(),
	schoolName = this.schoolName,
	grade = this.grade,
	bio = this.bio,
	interests = this.interests,
	avatarUrl = this.avatarUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyMember.join(user: UserJoined, company: CompanyJoined): CompanyMemberJoined = CompanyMemberJoined(
	user = user,
	company = company,
	role = role,
	status = status,
	joinedAt = joinedAt,
	createdAt = createdAt,
	updatedAt = updatedAt
)

fun CompanyMemberJoined.toRaw(): CompanyMember = CompanyMember(
	userId = this.user.id,
	companyId = this.company.id,
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyMemberJoined.toDto(): CompanyMemberDto = CompanyMemberDto(
	user = this.user.toDto(),
	company = this.company.toDto(),
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun CompanyMemberDto.toRaw(): CompanyMember = CompanyMember(
	userId = this.user.id,
	companyId = this.company.id,
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyMemberDto.toJoined(): CompanyMemberJoined = CompanyMemberJoined(
	user = this.user.toJoined(),
	company = this.company.toJoined(),
	role = this.role,
	status = this.status,
	joinedAt = this.joinedAt,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

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
	updatedAt = this.updatedAt
)

fun CompanyJoined.toRaw(): Company = Company(
	id = this.id,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyJoined.toDto(): CompanyDto = CompanyDto(
	id = this.id,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyDto.toRaw(): Company = Company(
	id = this.id,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun CompanyDto.toJoined(): CompanyJoined = CompanyJoined(
	id = this.id,
	name = this.name,
	industry = this.industry,
	website = this.website,
	logoUrl = this.logoUrl,
	country = this.country,
	city = this.city,
	about = this.about,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun Internship.join(company: CompanyJoined): InternshipJoined = InternshipJoined(
	id = this.id,
	company = company,
	title = title,
	category = category,
	description = description,
	createdAt = createdAt,
	updatedAt = updatedAt

)
fun InternshipJoined.toRaw(): Internship = Internship(
	id = this.id,
	companyId = this.company.id,
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipJoined.toDto(): InternshipDto = InternshipDto(
	id = this.id,
	company = this.company.toDto(),
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipDto.toRaw(): Internship = Internship(
	id = this.id,
	companyId = this.company.id,
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipDto.toJoined(): InternshipJoined = InternshipJoined(
	id = this.id,
	company = this.company.toJoined(),
	title = this.title,
	category = this.category,
	description = this.description,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipApplication.join(internship: InternshipJoined, student: StudentJoined): InternshipApplicationJoined =
	InternshipApplicationJoined(
		id = this.id,
		internship = internship,
		student = student,
		status = status,
		resume = resume,
		interviewNotes = interviewNotes,
		createdAt = createdAt,
		updatedAt = updatedAt
	)

fun InternshipApplicationJoined.toRaw(): InternshipApplication = InternshipApplication(
	id = this.id,
	internshipId = this.internship.id,
	studentId = this.student.user.id,
	status = this.status,
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)

fun InternshipApplicationJoined.toDto(): InternshipApplicationDto = InternshipApplicationDto(
	id = this.id,
	internship = this.internship.toDto(),
	student = this.student.toDto(),
	status = this.status.name,
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipApplicationDto.toRaw(): InternshipApplication = InternshipApplication(
	id = this.id,
	internshipId = this.internship.id,
	studentId = this.student.user.id,
	status = InternshipApplicationStatus.valueOf(this.status),
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)
fun InternshipApplicationDto.toJoined(): InternshipApplicationJoined = InternshipApplicationJoined(
	id = this.id,
	internship = this.internship.toJoined(),
	student = this.student.toJoined(),
	status = InternshipApplicationStatus.valueOf(this.status),
	resume = this.resume,
	interviewNotes = this.interviewNotes,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt
)