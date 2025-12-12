package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
	@Query("SELECT * FROM company LIMIT 1")
	fun findFirst(): Flow<Company?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(company: Company)

	@Delete
	suspend fun delete(company: Company)

	@Query("DELETE FROM company")
	suspend fun clearAll()
}