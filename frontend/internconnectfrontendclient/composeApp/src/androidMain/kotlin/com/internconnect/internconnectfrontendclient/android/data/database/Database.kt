package com.internconnect.internconnectfrontendclient.android.data.database

import android.content.Context
import androidx.room.*
import com.internconnect.internconnectfrontendclient.data.database.AppDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("database.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}