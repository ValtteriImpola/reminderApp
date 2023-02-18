package com.example.homeworkapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homeworkapp.ui.theme.NewMessage.NewMessage
import com.example.homeworkapp.ui.theme.home.Home
import com.example.homeworkapp.ui.theme.home.HomeViewModel
import com.example.homeworkapp.ui.theme.login.LoginScreen
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import com.example.homeworkapp.ui.theme.messageList.MessageListViewModel

@Composable
fun HomeWorkApp(
    viewModelLogIn: LoginViewModel,
    appState: HomeWorkAppState = rememberHomeWorkAppState(),
    viewModel: MessageListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),

) {
    NavHost(
        navController = appState.navController,
        startDestination = "loginScreen"
    )
    {
        composable(route = "loginScreen") {
            LoginScreen(modifier = Modifier.fillMaxSize(), navController = appState.navController, viewModelLogIn)
        }
        composable( route = "home") {
            Home(
                navController = appState.navController, viewModelLogIn
            )
        }
        composable( route = "message") {
            NewMessage(onBackPress = appState::navigateBack, navController = appState.navController, viewModelLogIn)
        }
    }
}