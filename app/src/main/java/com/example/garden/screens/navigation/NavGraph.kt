package com.example.garden.screens.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.archive.ArchiveScreen
import com.example.garden.screens.bed_creating.BedCreatingScreen
import com.example.garden.screens.bed_detail.BedDetailScreen
import com.example.garden.screens.beds_list.BedsListScreen
import com.example.garden.screens.calendar.CalendarScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
import com.example.garden.screens.notifications.NotificationScreen
import com.example.garden.screens.notifications_date.NotificationDateScreen



@Composable
fun SetUpOnGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Calendar.route)
    {
        composable(Destination.Calendar.route) {
            CalendarScreen()
        }
        composable(Destination.Archive.route) {
            ArchiveScreen()
        }
        composable(Destination.BedCreating.route) {
            BedCreatingScreen()
        }
        composable(Destination.BedDetail.route) {
            BedDetailScreen()
        }
        composable(Destination.BedEdit.route) {
            BedCreatingScreen()
        }
        composable(Destination.BedsList.route) {
            BedsListScreen()
        }
        composable(Destination.NotificationDetail.route) {
            NotificationDetailScreen()
        }
        composable(Destination.Notifications.route){
            NotificationScreen()
        }
        composable(Destination.NotificationDate.route) {
            NotificationDateScreen()
        }
    }
}
