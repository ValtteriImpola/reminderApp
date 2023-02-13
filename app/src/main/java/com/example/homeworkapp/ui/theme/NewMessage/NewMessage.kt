package com.example.homeworkapp.ui.theme.NewMessage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homeworkapp.ui.theme.messageList.MessageListViewModel


@Composable
fun NewMessage(
    onBackPress: () -> Unit,
    navController: NavController,
    viewModel: MessageListViewModel
) {
  //  val viewModel: MessageListViewModel = viewModel()
    val messageContent = remember { mutableStateOf("") }
    val messageTopic = remember { mutableStateOf("") }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Message")
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
                    onClick = { handleAddingNewMessage(viewModel, navController, messageContent.value, messageTopic.value) },
                    modifier = Modifier.fillMaxWidth().size(55.dp)
                ) {
                    Text("Save reminder")
                }
            }
        }

    }
}


private fun handleAddingNewMessage(
    viewModel: MessageListViewModel,
    navController: NavController,
    content: String,
    type: String
) {
    viewModel.inc(content, type)
    navController.navigate("home")
}

