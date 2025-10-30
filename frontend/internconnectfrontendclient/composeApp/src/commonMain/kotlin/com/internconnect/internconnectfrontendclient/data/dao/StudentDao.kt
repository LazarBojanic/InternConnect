package com.internconnect.internconnectfrontendclient.data.dao

import androidx.room.*
import com.internconnect.internconnectfrontendclient.data.model.Company
import com.internconnect.internconnectfrontendclient.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
	@Query("SELECT * FROM student WHERE userId = :id")
	suspend fun getStudentById(id: String): Student

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertStudent(student: Student)

	@Delete
	suspend fun deleteStudent(student: Student)

	@Query("SELECT * FROM student")
	fun getAllStudents(): Flow<List<Student>>
}