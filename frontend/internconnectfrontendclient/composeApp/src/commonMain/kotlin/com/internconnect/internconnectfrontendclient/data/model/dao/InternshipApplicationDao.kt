package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplication
import kotlinx.coroutines.flow.Flow

@Dao
interface InternshipApplicationDao {
	@Query("SELECT * FROM internship_application")
	fun findAll(): Flow<List<InternshipApplication>>

	@Query("SELECT * FROM internship_application WHERE student_id = :studentId")
	fun findAllByStudentId(studentId: String): Flow<List<InternshipApplication>>

	@Query("SELECT * FROM internship_application WHERE internship_id = :internshipId")
	fun findAllByInternshipId(internshipId: String): Flow<List<InternshipApplication>>

	@Query("SELECT * FROM internship_application WHERE id = :id LIMIT 1")
	fun findById(id: String): Flow<InternshipApplication?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(internshipApplication: InternshipApplication)

	@Delete
	suspend fun delete(internshipApplication: InternshipApplication)

	@Query("DELETE FROM internship_application")
	suspend fun clearAll()
}