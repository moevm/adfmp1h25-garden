package com.example.garden.screens.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garden.screens.navigation.Destination

@Composable
fun CalendarScreen(navController: NavHostController,
                   calendarViewModel: CalendarViewModel = hiltViewModel()
) {

    Column (){
        Text(
            text = "Calendar"
        )
        Button(onClick = {
            calendarViewModel.addNotification()
        }) {
            Text(
                "add",
                color = colorScheme.background
            )
        }
        Button(onClick = {
            navController.navigate(Destination.NotificationDate.route)
        }) {
            Text(
                "notification date",
                color = colorScheme.background
            )
        }
        calendarViewModel.listNotification.collectAsState().value.forEach{ el ->
            Text(text = el.title)

        }
    }
}