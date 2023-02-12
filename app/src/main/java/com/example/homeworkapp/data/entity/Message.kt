package com.example.homeworkapp.data.entity

import java.util.*

data class Message(
    val Id: Long,
    val messageContent: String,
    val messageDate: Date?,
    val messageType: String
)
