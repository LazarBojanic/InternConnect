package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.*
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

	@Query("SELECT * FROM student LIMIT 1")
	fun findFirst(): Flow<Student?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(student: Student)

	@Delete
	suspend fun delete(student: Student)

	@Query("DELETE FROM student")
	suspend fun clearAll()
}