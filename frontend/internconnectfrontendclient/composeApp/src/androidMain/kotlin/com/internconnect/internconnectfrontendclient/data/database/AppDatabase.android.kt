package com.internconnect.internconnectfrontendclient.data.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
	val appContext = context.applicationContext
	val dbFile = appContext.getDatabasePath("internconnect.db")
	return Room.databaseBuilder<AppDatabase>(
		context = appContext,
		name = dbFile.absolutePath
	)
}