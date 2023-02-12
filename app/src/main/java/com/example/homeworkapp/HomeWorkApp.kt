package com.example.homeworkapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homeworkapp.ui.theme.NewMessage.NewMessage
import com.example.homeworkapp.ui.theme.home.Home
import com.example.homeworkapp.ui.theme.login.LoginScreen
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import com.example.homeworkapp.ui.theme.messageList.MessageListViewModel

@Composable
fun HomeWorkApp(
    appState: HomeWorkAppState = rememberHomeWorkAppState(),
    viewModel: MessageListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    viewModelLogIn: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {

    //val viewState by viewModel.state.collectAsState()
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
                navController = appState.navController, viewModel
            )
        }
        composable( route = "message") {
            NewMessage(onBackPress = appState::navigateBack, navController = appState.navController, viewModel)
        }
    }
}