package com.example.homeworkapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.data.entity.room.CredentialsDao
import com.example.homeworkapp.data.entity.room.ReminderDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderRepository(
    private val reminderDao: ReminderDao
)
{
    val searchResults = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> = reminderDao.getCredentialsWithId(1)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertReminder(newReminder: Reminder) {
        coroutineScope.launch(Dispatchers.IO) {
            reminderDao.insert(newReminder)
        }
    }
    fun getCredentialsWithId(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            reminderDao.getCredentialsWithId(id)
        }
    }
}