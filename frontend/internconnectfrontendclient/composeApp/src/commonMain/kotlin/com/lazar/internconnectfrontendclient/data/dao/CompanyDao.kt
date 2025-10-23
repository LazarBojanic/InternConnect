package com.lazar.internconnectfrontendclient.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lazar.internconnectfrontendclient.data.model.Company
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
    @Insert
    suspend fun insert(item: Company)

    @Query("SELECT count(*) FROM Company")
    suspend fun count(): Int

    @Query("SELECT * FROM Company")
    fun getAllAsFlow(): Flow<List<Company>>
}