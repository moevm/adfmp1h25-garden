package com.example.garden.screens.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable





@Composable
fun SetUpOnGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, Destinations.Calendar){
        composable<Destinations.Calendar> {  }
    }
}
