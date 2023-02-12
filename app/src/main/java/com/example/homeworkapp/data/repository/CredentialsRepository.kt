package com.example.homeworkapp.data.repository

import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.room.CredentialsDao

class CredentialsRepository (
    private val credentialsDao: CredentialsDao
        )
{
    fun getCredentialsWithId(id: Long): Credentials? = credentialsDao.getCredentialsWithId(id)


    suspend fun addCredential(credentials: Credentials): Long {
        return when (val local = credentialsDao.getCredentialsWithId(credentials.id)) {
            null -> credentialsDao.insert(credentials)
            else -> local.id
        }
    }
}