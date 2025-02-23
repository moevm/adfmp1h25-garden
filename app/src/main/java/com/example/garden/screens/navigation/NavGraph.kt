package com.example.garden.screens.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.garden.screens.archive.ArchiveScreen
import com.example.garden.screens.bed_creating.BedCreatingScreen
import com.example.garden.screens.bed_detail.BedDetailScreen
import com.example.garden.screens.beds_list.BedsListScreen
import com.example.garden.screens.calendar.CalendarScreen
import com.example.garden.screens.home.HomeScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
import com.example.garden.screens.notifications.NotificationScreen
import com.example.garden.screens.notifications_date.NotificationDateScreen



@Composable
fun SetUpOnGraph(navController: NavHostController, navControllerBottom: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Destination.Home.route)
    {

        composable(Destination.BedCreating.route) {
            BedCreatingScreen()
        }
        composable(Destination.BedDetail.route) {
            BedDetailScreen()
        }
        composable(Destination.BedEdit.route) {
            BedCreatingScreen()
        }

        composable(Destination.NotificationDetail.route) {
            NotificationDetailScreen()
        }

        composable(Destination.NotificationDate.route) {
            NotificationDateScreen()
        }
        composable(Destination.Home.route) {
            HomeScreen(navController, navControllerBottom)
        }
    }
}
