package com.example.garden.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garden.screens.archive.ArchiveScreen
import com.example.garden.screens.beds_list.BedsListScreen
import com.example.garden.screens.calendar.CalendarScreen
import com.example.garden.screens.notifications.NotificationScreen

@Composable
fun BottomNavGraph(
    navControllerBottom: NavHostController,
    navController: NavHostController, innerPadding: Modifier
) {
    NavHost(navControllerBottom, startDestination = Destination.Calendar.route) {
        composable(Destination.Calendar.route) {
            CalendarScreen()
        }
        composable(Destination.Archive.route) {
            ArchiveScreen()
        }
        composable(Destination.BedsList.route) {
            BedsListScreen()
        }
        composable(Destination.Notifications.route){
            NotificationScreen()
        }

    }
}