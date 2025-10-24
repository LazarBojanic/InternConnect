package com.lazar.internconnectfrontendclient.data.dao

import androidx.room.*
import com.lazar.internconnectfrontendclient.data.model.Organization
import kotlinx.coroutines.flow.Flow

@Dao
interface OrganizationDao {
	@Insert
	suspend fun insertAll(vararg organizations: Organization)

	@Delete
	suspend fun delete(organization: Organization)

	@Query("SELECT * FROM organization")
	fun getAll(): Flow<List<Organization>>
}