package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import kotlinx.coroutines.flow.Flow

@Dao
interface InternshipDao {
	@Query("SELECT * FROM internship LIMIT 1")
	fun findFirst(): Flow<Internship?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(internship: Internship)

	@Delete
	suspend fun delete(internship: Internship)

	@Query("DELETE FROM internship")
	suspend fun clearAll()
}