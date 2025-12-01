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
	@Query("SELECT * FROM internship_application LIMIT 1")
	fun findFirst(): Flow<InternshipApplication?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(internshipApplication: InternshipApplication)

	@Delete
	suspend fun delete(internshipApplication: InternshipApplication)

	@Query("DELETE FROM internship_application")
	suspend fun clearAll()
}