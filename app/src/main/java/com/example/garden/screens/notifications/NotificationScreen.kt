package com.example.garden.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun NotificationScreen(
    navController:NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    Column (){
        Text(
            text = "NotificationDate"
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