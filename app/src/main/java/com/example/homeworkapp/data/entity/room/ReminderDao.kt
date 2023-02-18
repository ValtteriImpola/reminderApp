package com.example.homeworkapp.data.entity.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.homeworkapp.data.entity.Reminder

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminders WHERE id = :id")
    fun getCredentialsWithId(id: Long): LiveData<List<Reminder>>

//    @Query("SELECT * FROM credentials LIMIT 15")
//    abstract fun credentials(): Flow<List<Credentials>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Reminder): Long

}