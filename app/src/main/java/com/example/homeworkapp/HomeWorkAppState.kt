package com.example.homeworkapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.homeworkapp.ui.theme.messageList.MessageListViewModel

class HomeWorkAppState (
    val navController: NavHostController
   // val viewModel: MessageListViewModel
){
    fun navigateBack(){
        navController.popBackStack()
    }
}

@Composable
fun rememberHomeWorkAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    HomeWorkAppState(navController)
}