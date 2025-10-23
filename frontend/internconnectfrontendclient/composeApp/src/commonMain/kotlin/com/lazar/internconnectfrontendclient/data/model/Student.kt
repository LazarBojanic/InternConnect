package com.lazar.internconnectfrontendclient.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "pass")
    val pass: String,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "school")
    val school: String,
    @ColumnInfo(name = "yearOfStudy")
    val yearOfStudy: Long
)