package com.lazar.internconnectfrontendclient.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "pass")
    val pass: String,
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "industry")
    val industry: String,
    @ColumnInfo(name = "contactPerson")
    val contactPerson: String,
    @ColumnInfo(name = "contactPhone")
    val contactPhone: String,
)