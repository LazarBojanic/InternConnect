package com.lazar.internconnectfrontendclient.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val email: String,
    val pass: String,
    val companyName: String,
    val industry: String,
    val contactPerson: String,
    val contactPhone: String,
)