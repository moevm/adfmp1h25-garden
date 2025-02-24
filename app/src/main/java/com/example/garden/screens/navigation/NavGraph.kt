package com.example.garden.screens.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garden.screens.bed.BedCreatingScreen
import com.example.garden.screens.bed.BedDetailScreen
import com.example.garden.screens.home.HomeScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
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
