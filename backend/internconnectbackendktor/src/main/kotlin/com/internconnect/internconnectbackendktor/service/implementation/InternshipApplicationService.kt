package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplication
import com.internconnect.internconnectbackendktor.model.raw.internshipapplication.InternshipApplicationStatus
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyRepository
import com.internconnect.internconnectbackendktor.repository.specification.IInternshipApplicationRepository
import com.internconnect.internconnectbackendktor.repository.specification.IInternshipRepository
import com.internconnect.internconnectbackendktor.repository.specification.IStudentRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IInternshipApplicationService
import java.util.UUID

class InternshipApplicationService(
	private val companyRepository: ICompanyRepository,
	private val userRepository: IUserRepository,
	private val internshipRepository: IInternshipRepository,
	private val studentRepository: IStudentRepository,
	private val internshipApplicationRepository: IInternshipApplicationRepository,
) : IInternshipApplicationService {
	override suspend fun getAll(): List<InternshipApplicationJoined> {
		val internshipApplications = internshipApplicationRepository.findAll()
		val internshipApplicationsJoined = mutableListOf<InternshipApplicationJoined>()
		for(internshipApplication: InternshipApplication in internshipApplications){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					internshipApplicationsJoined.add(internshipApplication.join(internship.join(company.join()), student.join(user.join())))
				}
			}
		}
		return internshipApplicationsJoined
	}

	override suspend fun getById(id: UUID): InternshipApplicationJoined? {
		val internshipApplication = internshipApplicationRepository.findById(id)
		if(internshipApplication != null){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					return internshipApplication.join(internship.join(company.join()), student.join(user.join()))
				}
			}
		}
		return null
	}

	override suspend fun getByInternshipId(internshipId: UUID): List<InternshipApplicationJoined> {
		val internshipApplications = internshipApplicationRepository.findByInternshipId(internshipId)
		val internshipApplicationsJoined = mutableListOf<InternshipApplicationJoined>()
		for(internshipApplication: InternshipApplication in internshipApplications){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					internshipApplicationsJoined.add(internshipApplication.join(internship.join(company.join()), student.join(user.join())))
				}
			}
		}
		return internshipApplicationsJoined
	}

	override suspend fun getByStudentId(studentId: UUID): List<InternshipApplicationJoined> {
		val internshipApplications = internshipApplicationRepository.findByStudentId(studentId)
		val internshipApplicationsJoined = mutableListOf<InternshipApplicationJoined>()
		for(internshipApplication: InternshipApplication in internshipApplications){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					internshipApplicationsJoined.add(internshipApplication.join(internship.join(company.join()), student.join(user.join())))
				}
			}
		}
		return internshipApplicationsJoined

	}

	override suspend fun getByStatus(status: InternshipApplicationStatus): List<InternshipApplicationJoined> {
		val internshipApplications = internshipApplicationRepository.findByStatus(status)
		val internshipApplicationsJoined = mutableListOf<InternshipApplicationJoined>()
		for(internshipApplication: InternshipApplication in internshipApplications){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					internshipApplicationsJoined.add(internshipApplication.join(internship.join(company.join()), student.join(user.join())))
				}
			}
		}
		return internshipApplicationsJoined
	}

	override suspend fun create(internshipApplication: InternshipApplication): InternshipApplicationJoined? {
		val created = internshipApplicationRepository.create(internshipApplication)
		if(created != null){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					return created.join(internship.join(company.join()), student.join(user.join()))
				}
			}
		}
		return null
	}

	override suspend fun update(internshipApplication: InternshipApplication): InternshipApplicationJoined? {
		val updated = internshipApplicationRepository.update(internshipApplication)
		if(updated != null){
			val internship = internshipRepository.findById(internshipApplication.internshipId)
			val student = studentRepository.findById(internshipApplication.studentId)
			if(internship != null && student != null){
				val company = companyRepository.findById(internship.companyId)
				val user = userRepository.findById(student.userId)
				if(company != null && user != null){
					return updated.join(internship.join(company.join()), student.join(user.join()))
				}
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return internshipApplicationRepository.delete(id)
	}
}