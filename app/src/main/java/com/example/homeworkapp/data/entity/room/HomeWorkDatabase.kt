package com.example.homeworkapp.data.entity.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.homeworkapp.data.entity.Credentials


@androidx.room.Database(
    entities = [Credentials::class],
    version = 1,
    exportSchema = false
)
abstract class HomeWorkDatabase : RoomDatabase() {
    abstract fun credentialsDao(): CredentialsDao
    companion object {

        private var INSTANCE: HomeWorkDatabase? = null
        val PREPOPULATE_DATA = Credentials(id = 1, password = "1", username = "val")
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