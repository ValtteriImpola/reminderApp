package com.example.homeworkapp

import android.content.Context
import androidx.room.Room
import com.example.homeworkapp.data.entity.room.Database
import com.example.homeworkapp.data.repository.CredentialsRepository

object Graph {
    lateinit var database: Database
        private set

    val credentialRepository by lazy {
        CredentialsRepository(
            credentialsDao = database.credentialsDao()
        )
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, database::class.java, "data.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}