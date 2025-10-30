package com.internconnect.internconnectfrontendclient.data.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.internconnect.internconnectfrontendclient.data.dao.StudentDao
import com.internconnect.internconnectfrontendclient.data.model.Company
import com.internconnect.internconnectfrontendclient.data.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities =
	[Student::class, Company::class],
	version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getOrganizationDao(): StudentDao

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