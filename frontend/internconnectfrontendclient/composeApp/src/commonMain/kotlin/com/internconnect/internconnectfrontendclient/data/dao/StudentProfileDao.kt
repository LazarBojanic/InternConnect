package com.internconnect.internconnectfrontendclient.data.dao

import androidx.room.*
import com.internconnect.internconnectfrontendclient.data.model.StudentProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentProfileDao {
	@Query("SELECT * FROM student_profile WHERE userId = :id")
	suspend fun findById(id: String): StudentProfile?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(studentProfile: StudentProfile)

	@Delete
	suspend fun delete(studentProfile: StudentProfile)

	@Query("SELECT * FROM student_profile")
	fun findAll(): Flow<List<StudentProfile>>

	@Query("DELETE FROM student_profile")
	suspend fun clearAll()
}