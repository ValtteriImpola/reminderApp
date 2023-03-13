package com.example.homeworkapp.ui.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import com.example.homeworkapp.ui.util.rememberMapViewWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun ReminderLocationMap(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val mapView = rememberMapViewWithLifecycle()
    val coroutinesScope = rememberCoroutineScope()
    val viewState by viewModel.reminder.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 36.dp)
    ) {
        AndroidView({ mapView }) { mapView ->
            coroutinesScope.launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                val location = LatLng(65.006, 25.441)

                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(location, 15f)
                )
//                viewState.forEach {reminder ->
//                    val markerOptions = MarkerOptions()
//                        .title(reminder.message)
//                        .position(LatLng(reminder.location_x, reminder.location_y))
//                    map.addMarker(markerOptions)
//                }


                setMapLongClick(map = map, navController)

            }
        }
    }
}

private fun setMapLongClick(
    map: GoogleMap,
    navController: NavController
) {
    map.setOnMapLongClickListener { latlng ->
        val snippet = String.format(
            Locale.getDefault(),
            "Lat: %1$.2f, Lng: %2$.2f",
            latlng.latitude,
            latlng.longitude
        )

        map.addMarker(
            MarkerOptions().position(latlng).title("reminder location").snippet(snippet)
        ).apply {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("location_data", latlng)
            navController.popBackStack()
        }
    }
}