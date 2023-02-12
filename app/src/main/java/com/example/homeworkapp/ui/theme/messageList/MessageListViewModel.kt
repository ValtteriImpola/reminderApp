package com.example.homeworkapp.ui.theme.messageList

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.homeworkapp.data.entity.Message

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import java.util.*

class MessageListViewModel: ViewModel() {
    private val _state = MutableStateFlow(MessageListViewState())
    val state: StateFlow<MessageListViewState>
        get() = _state

    init {
        val list = mutableListOf<Message>()
        for (x in 1..20) {
            list.add(
                Message(
                    Id = x.toLong(),
                    messageContent = "$x Message",
                    messageDate = Date(),
                    messageType = "Reminder"
                )
            )
        }

        viewModelScope.launch {
            _state.value = MessageListViewState(
                messages = list
            )
        }
    }

    fun inc(
        content: String,
        type: String
    ) {
        val ttt = mutableListOf<Message>()

        val list = state.value.messages
        for (item in list) {
            ttt.add(
                Message(
                    Id = 11,
                    messageContent = item.messageContent ,
                    messageDate = Date(),
                    messageType = item.messageType
                )
            )
        }

        ttt.add(
            Message(
                Id = 11,
                messageContent = content ,
                messageDate = Date(),
                messageType = type
            )
        )
        _state.update {
            currentState -> currentState.copy(
            messages = ttt
            )
        }
    }
}

data class MessageListViewState(
    val messages: List<Message> = emptyList()
)
