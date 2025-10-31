package com.internconnect.internconnectfrontendclient.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.CompanyMemberProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyMemberProfileDao{
	@Query("SELECT * FROM company_member_profile WHERE userId = :id")
	suspend fun findById(id: String): CompanyMemberProfile?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(companyMemberProfile: CompanyMemberProfile)

	@Delete
	suspend fun delete(companyMemberProfile: CompanyMemberProfile)

	@Query("SELECT * FROM company_member_profile")
	fun findAll(): Flow<List<CompanyMemberProfile>>

	@Query("DELETE FROM company_member_profile")
	suspend fun clearAll()
}