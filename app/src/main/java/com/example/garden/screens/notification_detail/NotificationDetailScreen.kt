package com.example.garden.screens.notification_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.navigation.Destination

@Composable
fun NotificationDetailScreen(navController: NavHostController,dbViewModel: DBViewModel) {
    Column (){
        Text(
            text = "NotificationDetail"
        )
        Button(onClick = {

            navController.navigate(Destination.Home.route)
        }) {
            Text(text = "back")
        }
        Text(
            text = dbViewModel.note.collectAsState().value.title
        )
    }
}