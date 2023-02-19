package com.example.homeworkapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import java.util.*

@Composable
fun EditReminder (
    onBackPress: () -> Unit,
    navController: NavController,
    viewModel: LoginViewModel,
) {

    val viewState by viewModel.reminderInEdit.collectAsState()
    //val reminder by viewModel.searchReminderWithId()
   // val searchReults by viewModel.searchResults.observeAsState(listOf())
    val messageContent = remember { mutableStateOf(viewState.reminder.message) }
    val messageTopic = remember { mutableStateOf(viewState.reminder.type) }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = {navController.navigate("home")}
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Reminder")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)

            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = messageContent.value,
                    onValueChange = { text -> messageContent.value = text},
                    label = { Text(text = "Message topic") },
                    shape = RoundedCornerShape(corner = CornerSize(50.dp))
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = messageTopic.value,
                    onValueChange = { text -> messageTopic.value = text},
                    label = { Text(text = "Message content") },
                    shape = RoundedCornerShape(corner = CornerSize(50.dp))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { handleAddingNewMessage(viewModel, navController, viewState.reminder.id,  messageContent.value, messageTopic.value) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(55.dp)
                ) {
                    Text("Save reminder")
                }
            }
        }

    }
}


private fun handleAddingNewMessage(
    viewModel: LoginViewModel,
    navController: NavController,
    id: Long,
    content: String,
    type: String,

) {
    viewModel.insertReminder(Reminder( id = id,message = content, type = type, date = Date().toString()))
    navController.navigate("home")
}