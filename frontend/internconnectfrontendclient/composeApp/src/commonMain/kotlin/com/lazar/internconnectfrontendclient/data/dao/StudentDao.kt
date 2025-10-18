package com.lazar.internconnectfrontendclient.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lazar.internconnectfrontendclient.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(item: Student)

    @Query("SELECT count(*) FROM Student")
    suspend fun count(): Int

    @Query("SELECT * FROM Student")
    fun getAllAsFlow(): Flow<List<Student>>
}