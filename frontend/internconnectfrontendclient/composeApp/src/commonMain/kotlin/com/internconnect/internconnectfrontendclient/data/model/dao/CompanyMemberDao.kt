package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyMemberDao{
	@Query("SELECT * FROM company_member LIMIT 1")
	fun findFirst(): Flow<CompanyMember?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(companyMember: CompanyMember)

	@Delete
	suspend fun delete(companyMember: CompanyMember)

	@Query("DELETE FROM company_member")
	suspend fun clearAll()
}