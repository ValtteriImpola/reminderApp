package com.example.homeworkapp.ui.theme.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.homeworkapp.Graph
import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.repository.CredentialsRepository
import com.example.homeworkapp.ui.theme.messageList.MessageListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Optional.empty

class LoginViewModel(
    private val credentialsRepository: CredentialsRepository = Graph.credentialRepository
): ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state: StateFlow<LoginViewState>
        get() = _state


    init {
        viewModelScope.launch {
            _state.value = LoginViewState(
                credential = credentialsRepository.getCredentialsWithId(1)!!
            )
        }
        loadCredentialsFromDb()

    }
    private fun loadCredentialsFromDb( ) {
        val list = mutableListOf(
            Credentials(username = "valtteri", password = "salasana", id = 1)
        )
        
        viewModelScope.launch {
            list.forEach {
                    credentials -> credentialsRepository.addCredential(credentials)
            }
        }
    }
}
data class LoginViewState(
    val credential: Credentials = Credentials(id = 1, password = "s", username = "valtteri")
)
