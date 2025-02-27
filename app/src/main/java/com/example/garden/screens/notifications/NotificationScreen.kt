package com.example.garden.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.example.garden.screens.navigation.Destination

@Composable
fun NotificationScreen(
    navController:NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    var search_text by remember {
        mutableStateOf("")
    }

    Column (){
        TextField(value = search_text, onValueChange = {search_text = it})
        Text(
            text = "Notification"
        )
        Button(onClick = {
            navController.navigate(Destination.NotificationDetail.route)
        }) {
            Text(text = "detail")
        }

        notificationViewModel.notifications.collectAsState().value.forEach { el->
            Text(text = el.title)
        }
    }
}