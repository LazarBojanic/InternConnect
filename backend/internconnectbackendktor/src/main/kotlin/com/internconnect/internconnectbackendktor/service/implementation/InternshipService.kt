package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.InternshipJoined
import com.internconnect.internconnectbackendktor.model.raw.company.Company
import com.internconnect.internconnectbackendktor.model.raw.internship.Internship
import com.internconnect.internconnectbackendktor.repository.specification.ICompanyRepository
import com.internconnect.internconnectbackendktor.repository.specification.IInternshipRepository
import com.internconnect.internconnectbackendktor.service.specification.IInternshipService
import java.util.UUID

class InternshipService(
	private val companyRepository: ICompanyRepository,
	private val internshipRepository: IInternshipRepository,
) : IInternshipService {
	override suspend fun getAll(): List<InternshipJoined> {
		val internships = internshipRepository.findAll()
		val internshipsJoined = mutableListOf<InternshipJoined>()
		for(internship: Internship in internships) {
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				internshipsJoined.add(internship.join(company.join()))
			}
		}
		return internshipsJoined
	}

	override suspend fun getById(id: UUID): InternshipJoined? {
		val internship = internshipRepository.findById(id)
		if(internship != null){
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				return internship.join(company.join())
			}
		}
		return null
	}

	override suspend fun getByCompanyId(companyId: UUID): List<InternshipJoined> {
		val internships = internshipRepository.findByCompanyId(companyId)
		val internshipsJoined = mutableListOf<InternshipJoined>()
		for(internship: Internship in internships) {
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				internshipsJoined.add(internship.join(company.join()))
			}
		}
		return internshipsJoined
	}

	override suspend fun getByCategory(category: String): List<InternshipJoined> {
		val internships = internshipRepository.findByCategory(category)
		val internshipsJoined = mutableListOf<InternshipJoined>()
		for(internship: Internship in internships) {
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				internshipsJoined.add(internship.join(company.join()))
			}
		}
		return internshipsJoined
	}

	override suspend fun create(internship: Internship): InternshipJoined? {
		val created = internshipRepository.create(internship)
		if(created != null){
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				return created.join(company.join())
			}
		}
		return null
	}

	override suspend fun update(internship: Internship): InternshipJoined? {
		val updated = internshipRepository.update(internship)
		if(updated != null){
			val company = companyRepository.findById(internship.companyId)
			if(company != null){
				return updated.join(company.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return internshipRepository.delete(id)
	}
}