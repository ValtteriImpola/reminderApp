package com.example.homeworkapp.data.entity.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworkapp.data.entity.Credentials

@Dao
abstract class CredentialsDao {

    @Query(value = "SELECT * FROM credentials WHERE id = :id")
    abstract fun getCredentialsWithId(id: Long): Credentials?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Credentials): Long


}