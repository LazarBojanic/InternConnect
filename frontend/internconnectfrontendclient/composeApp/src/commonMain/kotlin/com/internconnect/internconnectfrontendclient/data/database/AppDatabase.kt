package com.internconnect.internconnectfrontendclient.data.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.internconnect.internconnectfrontendclient.data.dao.CompanyMemberDao
import com.internconnect.internconnectfrontendclient.data.dao.StudentDao
import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import kotlinx.coroutines.Dispatchers

@Database(entities =
	[Student::class, CompanyMember::class],
	version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getStudentDao(): StudentDao
	abstract fun getCompanyMemberDao(): CompanyMemberDao

}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getAppDatabase(
	builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
	return builder
		.setDriver(BundledSQLiteDriver())
		.setQueryCoroutineContext(Dispatchers.Default)
		.build()
}