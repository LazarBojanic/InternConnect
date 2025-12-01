package com.internconnect.internconnectfrontendclient.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import com.internconnect.internconnectfrontendclient.data.model.raw.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
	@Query("SELECT * FROM user LIMIT 1")
	fun findFirst(): Flow<User?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(user: User)

	@Delete
	suspend fun delete(user: User)

	@Query("DELETE FROM user")
	suspend fun clearAll()
}