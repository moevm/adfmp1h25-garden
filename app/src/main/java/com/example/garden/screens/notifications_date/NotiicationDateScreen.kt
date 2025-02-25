package com.example.garden.screens.notifications_date

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun NotificationDateScreen(navController: NavHostController) {
    Column (){
        Text(
            text = "NotificationDate"
        )
        Button(onClick = {
            navController.navigate(Destination.NotificationDetail.route)
        }) {
            Text(text = "detail")
        }
        Button(onClick = {
            navController.navigate(Destination.Home.route)
        }) {
            Text(text = "back")
        }
    }
}