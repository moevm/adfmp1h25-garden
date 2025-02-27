package com.example.garden.screens.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garden.screens.bed.BedCreatingScreen
import com.example.garden.screens.bed.BedDetailScreen
import com.example.garden.screens.bed.BedEditScreen
import com.example.garden.screens.BedViewModel
import com.example.garden.screens.home.HomeScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
import com.example.garden.screens.notifications_date.NotificationDateScreen



@Composable
fun SetUpOnGraph(navController: NavHostController, bedViewModel: BedViewModel) {


    NavHost(
        navController = navController,
        startDestination = Destination.Home.route)
    {

        composable(Destination.BedCreating.route) {
            BedCreatingScreen(navController,bedViewModel)
        }
        composable(
            route = Destination.BedDetail.route,

        ) {
            BedDetailScreen(navController,bedViewModel)
        }
        composable(Destination.BedEdit.route) {
            BedEditScreen(navController)
        }

        composable(Destination.NotificationDetail.route) {
            NotificationDetailScreen(navController)
        }

        composable(Destination.NotificationDate.route) {
            NotificationDateScreen(navController)
        }
        composable(Destination.Home.route) {
            Log.d("HOME","here")
            HomeScreen(navController,bedViewModel)
        }
    }
}
