package com.example.homeworkapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(
    tableName = "credentials",
    indices = [
        Index("id", unique = true)
    ]
)
class Credentials {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long = 1
    @ColumnInfo(name = "password")
    var password: String = ""
    @ColumnInfo(name = "username")
    var username: String = ""


    constructor() {}

    constructor(id: Long, username: String, password: String) {
        this.id = id
        this.password = password
        this.username = username
    }
}
