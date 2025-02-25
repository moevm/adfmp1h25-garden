package com.example.garden.screens.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun CalendarScreen(navController: NavHostController) {
    Column (){
        Text(
            text = "Calendar"
        )
        Button(onClick = {
            navController.navigate(Destination.NotificationDate.route)
        }) {
            Text("notification date")
        }
    }
}