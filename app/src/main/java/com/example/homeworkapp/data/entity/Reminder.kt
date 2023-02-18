package com.example.homeworkapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
import java.util.*

@Entity(
    tableName = "reminders",
    indices = [
        Index("id", unique = true)
    ]
)
class Reminder {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 1
    @ColumnInfo(name = "message")
    var message: String = ""
    @ColumnInfo(name = "type")
    var type: String = ""
    @ColumnInfo(name = "date")
    var date: String = ""


    constructor() {}

    constructor(id: Long, message: String, type: String, date: String) {
        this.id = id
        this.message = message
        this.type = type
        this.date = date
    }
}