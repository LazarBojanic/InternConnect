package com.lazar.internconnectfrontendclient.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val pass: String,
    val firstName: String,
    val lastName: String,
    val school: String,
    val yearOfStudy: Long
)