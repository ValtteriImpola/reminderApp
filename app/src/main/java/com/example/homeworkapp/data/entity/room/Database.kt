package com.example.homeworkapp.data.entity.room

import androidx.room.RoomDatabase
import com.example.homeworkapp.data.entity.Credentials


@androidx.room.Database(
    entities = [Credentials::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun credentialsDao(): CredentialsDao
}