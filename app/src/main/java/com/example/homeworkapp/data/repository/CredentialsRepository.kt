package com.example.homeworkapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.room.CredentialsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CredentialsRepository (
    private val credentialsDao: CredentialsDao
        )
{
    val searchResults = MutableLiveData<List<Credentials>>()
    val credentials: LiveData<List<Credentials>> = credentialsDao.getCredentialsWithId(1)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(newCredential: Credentials) {
        coroutineScope.launch(Dispatchers.IO) {
            credentialsDao.insert(newCredential)
        }
    }
    fun getCredentialsWithId(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            credentialsDao.getCredentialsWithId(id)
        }
    }
}