package com.lazar.internconnectfrontendclient.data.dao

import androidx.room.*
import com.lazar.internconnectfrontendclient.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(item: NoteEntity)

    @Query("SELECT count(*) FROM NoteEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM NoteEntity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>
}