package com.example.homeworkapp.ui.theme.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import com.example.homeworkapp.R
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import com.example.homeworkapp.ui.theme.messageList.MessagePage

@Composable
fun Home(
    navController: NavController,
    viewModel: LoginViewModel
) {

    HomeContent(navController = navController, viewModel)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeContent(
    navController: NavController,
    viewModel: LoginViewModel
) {
    Scaffold(
        modifier = Modifier.padding(bottom =24.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "message") },
                contentColor = Color.Blue,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = MaterialTheme.colors.secondary.copy(alpha = 0.87f)

            HomeAppBar(
                backgroundColor = appBarColor, navController
            )

            MessagePage(viewModel, navController)
        }
    }
}

@Composable
private fun HomeAppBar(
    backgroundColor: Color,
    navController: NavController
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {} ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
            IconButton( onClick = {navController.navigate(route = "loginScreen") } ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "account")
            }
        }
    )
}
