package com.example.homeworkapp.data.entity

import android.net.Uri
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
    @ColumnInfo(name = "reminder_seen")
    var reminder_seen: Boolean = false
    @ColumnInfo(name = "reminder_time")
    var reminder_time: String = ""
    @ColumnInfo(name = "location_x")
    var location_x: Double = 0.0
    @ColumnInfo(name = "location_y")
    var location_y: Double = 0.0
    @ColumnInfo(name = "creator_id")
    var creator_id: Long = 0
    @ColumnInfo(name = "image_data")
    var image_data: String = ""
    @ColumnInfo(name = "show_reminder")
    var show_reminder: Boolean = false


    constructor() {}

    constructor(id: Long, message: String, reminder_seen: Boolean, reminder_time: String,
                location_x: Double, location_y: Double, creator_id: Long, image_data: String, show_reminder: Boolean) {
        this.id = id
        this.message = message
        this.reminder_seen = reminder_seen
        this.reminder_time = reminder_time
        this.location_x = location_x
        this.location_y = location_y
        this.creator_id = creator_id
        this.image_data = image_data
        this.show_reminder = show_reminder

    }
}