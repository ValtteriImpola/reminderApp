package com.example.homeworkapp.data.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.Reminder


@androidx.room.Database(
    entities = [Credentials::class, Reminder::class],
    version = 7,
    exportSchema = false
)
abstract class HomeWorkDatabase : RoomDatabase() {
    abstract fun credentialsDao(): CredentialsDao
    abstract fun reminderDao(): ReminderDao
    companion object {

        private var INSTANCE: HomeWorkDatabase? = null

        fun getInstance(context: Context): HomeWorkDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HomeWorkDatabase::class.java,
                        "product_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}