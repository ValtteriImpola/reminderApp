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
    fun getRemiderWithId(id: Long): List<Reminder>

//    @Query("SELECT * FROM credentials LIMIT 15")
//    abstract fun credentials(): Flow<List<Credentials>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Reminder): Long

    @Query("SELECT * FROM reminders WHERE show_reminder = 1")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("DELETE FROM reminders WHERE id = :id")
    fun deleteReminder(id: Long)
}