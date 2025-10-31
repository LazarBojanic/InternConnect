package com.internconnect.internconnectfrontendclient.data.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.internconnect.internconnectfrontendclient.data.dao.CompanyMemberProfileDao
import com.internconnect.internconnectfrontendclient.data.dao.StudentProfileDao
import com.internconnect.internconnectfrontendclient.data.model.CompanyMemberProfile
import com.internconnect.internconnectfrontendclient.data.model.StudentProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities =
	[StudentProfile::class, CompanyMemberProfile::class],
	version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getStudentProfileDao(): StudentProfileDao
	abstract fun getCompanyMemberProfileDao(): CompanyMemberProfileDao

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
		.setQueryCoroutineContext(Dispatchers.IO)
		.build()
}