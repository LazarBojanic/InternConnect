package com.internconnect.internconnectbackendktor.service.implementation

import com.internconnect.internconnectbackendktor.model.join
import com.internconnect.internconnectbackendktor.model.joined.StudentJoined
import com.internconnect.internconnectbackendktor.model.raw.student.Student
import com.internconnect.internconnectbackendktor.repository.specification.IStudentRepository
import com.internconnect.internconnectbackendktor.repository.specification.IUserRepository
import com.internconnect.internconnectbackendktor.service.specification.IStudentService
import java.util.*

class StudentService(
	private val userRepository: IUserRepository,
	private val studentRepository: IStudentRepository,
) : IStudentService {
	override suspend fun getAll(): List<StudentJoined> {
		val students = studentRepository.findAll()
		val studentsJoined = mutableListOf<StudentJoined>()
		for(student: Student in students){
			val user = userRepository.findById(student.userId)
			if(user != null){
				studentsJoined.add(student.join(user.join()))
			}
		}
		return studentsJoined
	}

	override suspend fun getById(id: UUID): StudentJoined? {
		val student = studentRepository.findById(id)
		if(student != null){
			val user = userRepository.findById(student.userId)
			if(user != null){
				return student.join(user.join())
			}
		}
		return null
	}

	override suspend fun create(student: Student): StudentJoined? {
		val created = studentRepository.create(student)
		if(created != null){
			val user = userRepository.findById(created.userId)
			if(user != null){
				return created.join(user.join())
			}
		}
		return null
	}

	override suspend fun update(student: Student): StudentJoined? {
		val updated = studentRepository.update(student)
		if(updated != null){
			val user = userRepository.findById(updated.userId)
			if(user != null){
				return updated.join(user.join())
			}
		}
		return null
	}

	override suspend fun delete(id: UUID): Boolean {
		return studentRepository.delete(id)
	}
}