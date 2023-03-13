package com.example.homeworkapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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
   // val messageTopic = remember { mutableStateOf(viewState.reminder.) }
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
                if (viewState.reminder.show_reminder == true) {
                    Text(text = "Home")
                }
                else {
                    Text(text = "pöö")
                }

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
                    label = { Text(text = "Message content") },
                    shape = RoundedCornerShape(corner = CornerSize(50.dp))
                )
                        Image(
                            painter = rememberImagePainter(viewState.reminder.image_data.toUri()),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp, 8.dp)
                                .size(200.dp)
                                .clickable {

                                }
                        )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { handleAddingNewMessage(viewModel, navController, viewState.reminder.id,  messageContent.value, viewState.reminder.image_data, viewState.reminder.location_y,viewState.reminder.location_x) },
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
    image_data: String,
    lng: Double?,
    lat: Double?
) {
    val generatedID: Int = (10..10000).random()
    val latitude = lat ?: 0.0
    val longitude = lng ?: 0.0
    viewModel.insertReminder(Reminder( id = id,message = content, reminder_seen = true,
        reminder_time = Date().toString(), location_x = latitude, location_y = longitude, creator_id = 1, image_data, true), viewModel,0, false)
    navController.navigate("home")
}