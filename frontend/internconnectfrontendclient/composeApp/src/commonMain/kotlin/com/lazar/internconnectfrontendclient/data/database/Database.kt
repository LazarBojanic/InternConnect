package com.lazar.internconnectfrontendclient.data.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.lazar.internconnectfrontendclient.data.dao.CompanyDao
import com.lazar.internconnectfrontendclient.data.dao.StudentDao
import com.lazar.internconnectfrontendclient.data.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [NoteEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getStudentDao(): StudentDao
    abstract fun getCompanyDao(): CompanyDao

}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}