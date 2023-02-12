package com.example.homeworkapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "credentials",
    indices = [
        Index("id", unique = true)
    ]
)
data class Credentials(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "username") val username: String,
)
