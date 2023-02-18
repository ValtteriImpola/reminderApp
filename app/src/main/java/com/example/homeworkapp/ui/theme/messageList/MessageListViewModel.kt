package com.example.homeworkapp.ui.theme.messageList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.homeworkapp.data.entity.Message
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.data.entity.room.HomeWorkDatabase
import com.example.homeworkapp.data.entity.room.ReminderDatabase
import com.example.homeworkapp.data.repository.ReminderRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import java.util.*

class MessageListViewModel(): ViewModel() {
    private val _state = MutableStateFlow(MessageListViewState())
    val state: StateFlow<MessageListViewState>
        get() = _state

//      val reminder: LiveData<List<Reminder>>
//      private val repository: ReminderRepository

//    init {
//        val reminderDb = ReminderDatabase.getInstance(application)
//        val reminderDao = reminderDb.reminderDao()
//        repository = ReminderRepository(reminderDao)
//        //  insertProduct(Credentials(id=1, password = "password", username = "user"))
//       // reminder = repository.reminders
//    }


    fun insertReminder(reminder: Reminder) {
//        repository.insertProduct(reminder)
    }

    fun findProduct(id: Long) {
//        repository.getCredentialsWithId(id)
    }
//    init {
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
//
//        viewModelScope.launch {
//            _state.value = MessageListViewState(
//                messages = list
//            )
//        }
//    }

    fun inc(
        content: String,
        type: String
    ) {
//        val ttt = mutableListOf<Message>()
//
//        val list = state.value.messages
//        for (item in list) {
//            ttt.add(
//                Message(
//                    Id = 11,
//                    messageContent = item.messageContent ,
//                    messageDate = Date(),
//                    messageType = item.messageType
//                )
//            )
//        }
//
//        ttt.add(
//            Message(
//                Id = 11,
//                messageContent = content ,
//                messageDate = Date(),
//                messageType = type
//            )
//        )
//        _state.update {
//            currentState -> currentState.copy(
//            messages = ttt
//            )
//        }
    }

//    private fun loadRemindersFromDb(){
//
//    }
}

data class MessageListViewState(
    val messages: List<Message> = emptyList()
)
