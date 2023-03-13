package com.example.homeworkapp.ui.theme.NewMessage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import com.google.android.gms.maps.model.LatLng
import java.util.*

@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewMessage(
    onBackPress: () -> Unit,
    navController: NavController,
    viewModel: LoginViewModel
) {
    val messageContent = remember { mutableStateOf("") }
    val useNotification = remember { mutableStateOf(false) }
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val timeUntilNotification = remember { mutableStateOf("0") }

    val latlng = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<LatLng>("location_data")
        ?.value

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }
  //  val messageTopic = remember { mutableStateOf("") }
    var savedUri: Uri
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
                    label = { Text(text = "Message content") },
                    shape = RoundedCornerShape(corner = CornerSize(50.dp))
                )

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                ) {
                    Text(text = "Pick Image From Gallery")
                }

                LazyVerticalGrid(GridCells.Fixed(1)) {
                    items(selectImages) { uri ->
                        Image(
                            painter = rememberImagePainter(uri),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp, 8.dp)
                                .size(100.dp)
                                .clickable {

                                }
                        )
                        savedUri = uri
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row( modifier = Modifier.padding(16.dp)) {
                    Text(text = "Add notification")
                    RadioButton(selected = useNotification.value, onClick = { useNotification.value = !useNotification.value})
                }

                if (useNotification.value){
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = timeUntilNotification.value,
                        onValueChange = { timeString -> timeUntilNotification.value = timeString},
                        label = { Text(text = "Seconds until notification") },
                        shape = RoundedCornerShape(corner = CornerSize(50.dp))
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if(latlng == null)
                {
                    OutlinedButton(
                        onClick = { navController.navigate("map") },
                        modifier = Modifier.height(55.dp)
                    ) {
                        Text(text = "Reminder location")
                    }
                } else {
                    Text(
                        text = "Lat: ${latlng.latitude}, \n Lng: ${latlng.longitude}"
                    )
                }


                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { handleAddingNewMessage(viewModel, navController, messageContent.value,
                        selectImages[0], timeUntilNotification.value.toInt(), useNotification.value, latlng?.longitude , latlng?.latitude) },
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
    content: String,
    uri: Uri,
    timeToExecute: Int,
    useNotification: Boolean,
    lng: Double?,
    lat: Double?
) {
    val generatedID: Int = (10..10000).random()
    val latitude = lat ?: 0.0
    val longitude = lng ?: 0.0
    viewModel.insertReminder(Reminder( id = generatedID.toLong(),message = content, reminder_seen = false,
        reminder_time = Date().toString(), location_x = latitude, location_y = longitude, creator_id = 1, uri.toString(),
        !useNotification), viewModel, timeToExecute, useNotification)
    navController.navigate("home")
}

