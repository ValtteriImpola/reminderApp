package com.example.homeworkapp.ui.theme.login

import android.app.Application
import androidx.lifecycle.*

import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.room.HomeWorkDatabase
import com.example.homeworkapp.data.repository.CredentialsRepository

class LoginViewModel(
    application: Application
): ViewModel() {

    val credentials: LiveData<List<Credentials>>
    private val repository: CredentialsRepository

    init {
        val credentialDb = HomeWorkDatabase.getInstance(application)
        val credentialDao = credentialDb.credentialsDao()
        repository = CredentialsRepository(credentialDao)
        insertProduct(Credentials(id=1, password = "password", username = "user"))
        credentials = repository.credentials
    }

    fun insertProduct(credential: Credentials) {
        repository.insertProduct(credential)
    }

    fun findProduct(id: Long) {
        repository.getCredentialsWithId(id)
    }
}



