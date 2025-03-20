package com.example.garden.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garden.screens.DBViewModel
import com.example.garden.screens.about.AboutScreen
import com.example.garden.screens.archive.ArchiveScreen
import com.example.garden.screens.bed_list.BedsListScreen
import com.example.garden.screens.calendar.CalendarScreen
import com.example.garden.screens.notifications.NotificationScreen

@Composable
fun BottomNavGraph(
    navControllerBottom: NavHostController,
    navController: NavHostController, innerPadding: Modifier, dbViewModel: DBViewModel
) {
    NavHost(navControllerBottom, startDestination = Destination.Calendar.route) {
        composable(Destination.Calendar.route) {
            CalendarScreen(navController, dbViewModel)
        }
        composable(Destination.Archive.route) {
            ArchiveScreen(dbViewModel)
        }
        composable(Destination.BedsList.route) {
            BedsListScreen(navController, dbViewModel)
        }
        composable(Destination.Notifications.route) {
            NotificationScreen(navController, dbViewModel)
        }
        composable(Destination.About.route) {
            AboutScreen()
        }
    }
}