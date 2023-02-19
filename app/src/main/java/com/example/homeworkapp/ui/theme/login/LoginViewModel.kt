package com.example.homeworkapp.ui.theme.login

import android.app.Application
import androidx.lifecycle.*

import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.Message
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.data.entity.room.HomeWorkDatabase
import com.example.homeworkapp.data.repository.CredentialsRepository
import com.example.homeworkapp.data.repository.ReminderRepository
import com.example.homeworkapp.ui.theme.messageList.MessageListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class LoginViewModel(
    application: Application
): ViewModel() {

    val credentials: LiveData<List<Credentials>>
    private val repository: CredentialsRepository
    val searchResults: MutableLiveData<List<Reminder>>
    val reminder: LiveData<List<Reminder>>
    private val repositoryReminder: ReminderRepository
    private val _state = MutableStateFlow(MessageListViewState())
    private val _reminderInEdit = MutableStateFlow(ReminderInEditViewState())
    val state: StateFlow<MessageListViewState>
        get() = _state

    val reminderInEdit: StateFlow<ReminderInEditViewState>
        get() = _reminderInEdit
    init {
        val credentialDb = HomeWorkDatabase.getInstance(application)
        val credentialDao = credentialDb.credentialsDao()
        repository = CredentialsRepository(credentialDao)
        insertProduct(Credentials(id=1, password = "password", username = "user"))
        credentials = repository.credentials

        val reminderDb = HomeWorkDatabase.getInstance(application)
        val reminderDao = reminderDb.reminderDao()
        repositoryReminder = ReminderRepository(reminderDao)
        reminder = repositoryReminder.reminders

        searchResults = repositoryReminder.searchResults

    }

    fun insertReminder(reminder: Reminder) {
        repositoryReminder.insertReminder(reminder)
    }

    fun searchReminderWithId(id: Long){
        repositoryReminder.getReminderWithId(id)
    }
    fun insertProduct(credential: Credentials) {
        repository.insertProduct(credential)
    }

    fun findProduct(id: Long) {
        repository.getCredentialsWithId(id)
    }

    fun deleteReminder(id: Long) {
        repositoryReminder.deleteReminder(id)
    }

    fun setReminderInEdit(reminder: Reminder) {
        viewModelScope.launch {
            _reminderInEdit.value = ReminderInEditViewState(
                reminder = reminder
            )
        }
    }
}

data class MessageListViewState(
    val messages: List<Message> = emptyList()
)
data class ReminderInEditViewState(
    val reminder: Reminder = Reminder()
)

