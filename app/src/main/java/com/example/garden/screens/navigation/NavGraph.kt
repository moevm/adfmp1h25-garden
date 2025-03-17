package com.example.garden.screens.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.bed_creating.BedCreatingScreen
import com.example.garden.screens.bed_detail.BedDetailScreen
import com.example.garden.screens.bed_edit.BedEditScreen
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.about.AboutScreen
import com.example.garden.screens.home.HomeScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
import com.example.garden.screens.notifications_date.NotificationDateScreen


@Composable
fun SetUpOnGraph(navController: NavHostController, dbViewModel: DBViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    )
    {
        composable(Destination.BedCreating.route) {
            BedCreatingScreen(navController, dbViewModel)
        }
        composable(
            route = Destination.BedDetail.route,
        ) {
            BedDetailScreen(navController, dbViewModel)
        }
        composable(Destination.BedEdit.route) {
            BedEditScreen(navController, dbViewModel)
        }
        composable(Destination.NotificationDetail.route) {
            NotificationDetailScreen(navController, dbViewModel)
        }
        composable(Destination.NotificationDate.route) {
            NotificationDateScreen(navController, dbViewModel)
        }
        composable(Destination.Home.route) {
            HomeScreen(navController, dbViewModel)
        }

        composable(Destination.About.route) {
            AboutScreen()
        }
    }
}
