package com.example.garden.screens.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.garden.screens.bed.BedCreatingScreen
import com.example.garden.screens.bed.BedDetailScreen
import com.example.garden.screens.bed.BedEditScreen
import com.example.garden.screens.bed.BedViewModel
import com.example.garden.screens.home.HomeScreen
import com.example.garden.screens.notification_detail.NotificationDetailScreen
import com.example.garden.screens.notifications_date.NotificationDateScreen



@Composable
fun SetUpOnGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Destination.Home.route)
    {

        composable(Destination.BedCreating.route) {
            BedCreatingScreen(navController)
        }
        composable(
            route = Destination.BedDetail.route+"/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id") ?: ""
            BedDetailScreen(navController,id)
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
            HomeScreen(navController)
        }
    }
}
