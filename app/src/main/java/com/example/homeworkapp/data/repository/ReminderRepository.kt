package com.example.homeworkapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.data.entity.room.CredentialsDao
import com.example.homeworkapp.data.entity.room.ReminderDao
import kotlinx.coroutines.*

class ReminderRepository(
    private val reminderDao: ReminderDao
)
{
    val searchResults = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> = reminderDao.getAllReminders()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertReminder(newReminder: Reminder) {
        coroutineScope.launch(Dispatchers.IO) {
            reminderDao.insert(newReminder)
        }
    }
    fun getReminderWithId(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            searchResults.value = asyncFind(id).await()
        }
    }

    fun deleteReminder(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            reminderDao.deleteReminder(id)
        }
    }
    private fun asyncFind(id: Long): Deferred<List<Reminder>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async reminderDao.getRemiderWithId(id)
        }
}