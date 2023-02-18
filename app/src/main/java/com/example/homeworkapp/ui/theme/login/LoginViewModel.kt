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

    val reminder: LiveData<List<Reminder>>
    private val repositoryReminder: ReminderRepository
    private val _state = MutableStateFlow(MessageListViewState())
    val state: StateFlow<MessageListViewState>
        get() = _state

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


//        val list = mutableListOf<Message>()
//        for (x in 1..20) {
//            list.add(
//                Message(
//                    Id = x.toLong(),
//                    messageContent = "$x Message",
//                    messageDate = Date(),
//                    messageType = "Reminder"
//                )
//            )
//        }
//        viewModelScope.launch {
//            _state.value = MessageListViewState(
//                messages = reminders
//            )
//        }
    }

    fun insertReminder(reminder: Reminder) {
        repositoryReminder.insertReminder(reminder)
    }

//    fun findProduct(id: Long) {
////        repository.getCredentialsWithId(id)
//    }


    fun insertProduct(credential: Credentials) {
        repository.insertProduct(credential)
    }

    fun findProduct(id: Long) {
        repository.getCredentialsWithId(id)
    }
}

data class MessageListViewState(
    val messages: List<Message> = emptyList()
)

