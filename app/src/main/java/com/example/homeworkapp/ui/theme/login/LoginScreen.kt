package com.example.homeworkapp.ui.theme.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LoginScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel
) {
    val viewState by viewModel.state.collectAsState()
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column (
        modifier = modifier.padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center

    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_foreground),
//            contentDescription = "login_image",
//            modifier = Modifier.fillMaxWidth(),
//            alignment = Alignment.Center
//        )
        Icon(painter = rememberVectorPainter(Icons.Filled.Person),
            contentDescription = "Login_image",
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username.value,
            onValueChange = { text -> username.value = text},
            label = { Text(text="Username")},
            shape = RoundedCornerShape(corner = CornerSize(50.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = { passwordString -> password.value = passwordString},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(corner = CornerSize(50.dp))

        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
//                scope.launch {
//                   dataStore.saveEmail("sss")
//                        }
               ValidateCredentials(navController, username.value, password.value, viewState.credential.username)


                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(corner = CornerSize(50.dp))
        ) {
            Text(text="Login")
        }
    }
}

private fun ValidateCredentials(
    navController: NavController,
    username: String,
    password: String,
    savedUsername: String?
) {

    if(username == savedUsername){
        navController.navigate("home")
    }
}

/*
@Composable
fun Login(
   // navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()){


    }
}*/

/*
@Preview
@Composable
fun Preview() {
    LoginScreen(modifier = Modifier, navController = NavController() )
}*/
