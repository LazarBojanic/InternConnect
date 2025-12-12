package com.internconnect.internconnectfrontendclient.data.database

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.internconnect.internconnectfrontendclient.data.model.dao.CompanyDao
import com.internconnect.internconnectfrontendclient.data.model.dao.CompanyMemberDao
import com.internconnect.internconnectfrontendclient.data.model.dao.InternshipApplicationDao
import com.internconnect.internconnectfrontendclient.data.model.dao.InternshipDao
import com.internconnect.internconnectfrontendclient.data.model.dao.StudentDao
import com.internconnect.internconnectfrontendclient.data.model.dao.UserDao
import com.internconnect.internconnectfrontendclient.data.model.raw.Company
import com.internconnect.internconnectfrontendclient.data.model.raw.CompanyMember
import com.internconnect.internconnectfrontendclient.data.model.raw.Internship
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplication
import com.internconnect.internconnectfrontendclient.data.model.raw.Student
import com.internconnect.internconnectfrontendclient.data.model.raw.User
import kotlinx.coroutines.Dispatchers

@Database(entities =
	[User::class, Student::class, CompanyMember::class, Company::class, Internship::class, InternshipApplication::class],
	version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getUserDao(): UserDao
	abstract fun getStudentDao(): StudentDao
	abstract fun getCompanyMemberDao(): CompanyMemberDao
	abstract fun getCompanyDao(): CompanyDao
	abstract fun getInternshipDao(): InternshipDao
	abstract fun getInternshipApplicationDao(): InternshipApplicationDao

}

@Suppress("KotlinNoActualForExpect") expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
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