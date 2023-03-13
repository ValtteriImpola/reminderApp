package com.example.homeworkapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homeworkapp.ui.EditReminder
import com.example.homeworkapp.ui.maps.ReminderLocationMap
import com.example.homeworkapp.ui.maps.SearchMap
import com.example.homeworkapp.ui.theme.NewMessage.NewMessage
import com.example.homeworkapp.ui.theme.home.Home
import com.example.homeworkapp.ui.theme.home.HomeViewModel
import com.example.homeworkapp.ui.theme.login.LoginScreen
import com.example.homeworkapp.ui.theme.login.LoginViewModel
@Composable
fun HomeWorkApp(
    viewModelLogIn: LoginViewModel,
    appState: HomeWorkAppState = rememberHomeWorkAppState(),
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
        composable( route = "editReminder") {
            EditReminder(onBackPress = appState::navigateBack, navController = appState.navController, viewModelLogIn)
        }
        composable( route = "searchMap") {
            SearchMap(navController = appState.navController, viewModelLogIn)
        }
        composable( route = "map") {
            ReminderLocationMap(navController = appState.navController, viewModelLogIn)
        }
    }
}